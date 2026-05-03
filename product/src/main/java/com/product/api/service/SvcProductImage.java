package com.product.api.service;

import java.util.List;
import com.product.api.entity.ProductImage;

public interface SvcProductImage {
    public List<ProductImage> getProductImages(Integer product_id);
    public void upload(ProductImage in); 
    public void deleteProductImage(Integer product_image_id);
}