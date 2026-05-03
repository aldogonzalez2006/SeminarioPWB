package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcProductImageImp implements SvcProductImage {

    @Autowired
    RepoProductImage repo;

    @Value("${app.upload.dir}")
    private String uploadDir; // uploads

    @Value("${app.upload.images}")
    private String uploadImages; // img

    @Override
    public List<ProductImage> getProductImages(Integer product_id) {
        try {
            return repo.findByProductIdAndStatus(product_id,1);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    @Override
    public void upload(ProductImage in) {
        try {
            // Validar prefijo del String de Base64
            if (in.getImage().startsWith("data:image")) {
                int commaIndex = in.getImage().indexOf(",");
                if (commaIndex != -1) {
                    in.setImage(in.getImage().substring(commaIndex + 1));
                }
            }
            // Convertir el Base64 en un File
            byte[] imageBytes = Base64.getDecoder().decode(in.getImage());
            String fileName = UUID.randomUUID().toString() + ".png";

            // Construye la ruta completa: uploads/img/product/nombrefile.png
            Path imagePath = Paths.get(uploadDir, uploadImages, "product", fileName);

            // Guardar el File en el sistema de archivos
            Files.createDirectories(imagePath.getParent());
            Files.write(imagePath, imageBytes);

            // Guardar la ruta en la base de datos
            ProductImage productImage = new ProductImage();
            productImage.setProductId(in.getProductId());
            productImage.setImage("/product/" + fileName); // Ruta relativa
            productImage.setStatus(1);

            repo.save(productImage);

        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El string de la imagen no es un Base64 válido.");
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        } catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
        }
    }

    @Override
    public void deleteProductImage(Integer product_image_id) {
        try {
            if (!repo.existsById(product_image_id)) {
                throw new ApiException(HttpStatus.NOT_FOUND, "El id de la imagen no existe");
            }
            repo.deleteById(product_image_id);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
}