package com.product.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.product.api.entity.ProductImage;
import com.product.api.service.SvcProductImage;

@RestController
@RequestMapping("/product")
public class CtrlProductImage {

    @Autowired
    SvcProductImage svc;

    @GetMapping("/{id}/image")
    public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable("id") Integer product_id) {
        return ResponseEntity.ok(svc.getProductImages(product_id));
    }

  @PostMapping("/{id}/image")
public ResponseEntity<String> createProductImage(@PathVariable("id") Integer product_id, @RequestBody ProductImage image) {
    // Cambia setProduct_id por setProductId
    image.setProductId(product_id); 
    svc.upload(image);
    return ResponseEntity.ok("La imagen ha sido registrada");
}

    @DeleteMapping("/{id}/image/{product_image_id}")
    public ResponseEntity<String> deleteProductImage(@PathVariable("product_image_id") Integer product_image_id) {
        svc.deleteProductImage(product_image_id);
        return ResponseEntity.ok("La imagen ha sido eliminada");
    }
}