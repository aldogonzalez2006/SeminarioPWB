package com.product.api.service;

import java.util.List;
import com.product.api.entity.Category;
import org.springframework.http.ResponseEntity;
/**
 * Actualizamos la clase, donde soliamos tener public List<Category> getCategories();
 */
public interface SvcCategory {
    public ResponseEntity<List<Category>> getCategories();
    
}