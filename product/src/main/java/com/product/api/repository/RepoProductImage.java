package com.product.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.product.api.entity.ProductImage;

@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {
    // Para el GET: buscar todas las imágenes de un producto que estén activas (status 1)
    List<ProductImage> findByProductIdAndStatus(Integer product_id, Integer status);
}