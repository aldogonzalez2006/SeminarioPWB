package com.product.api.controller;
/**
 * Tambien actualizamos esta
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.product.api.service.SvcCategory;
import com.product.api.entity.Category;

/**
 * Endpoint para manejar las peticiones de categorías.
 */
@RestController
public class CtrlCategory {

    @Autowired
    private SvcCategory svc;

    @GetMapping("/category")
    public List<Category> getCategories() {
        return svc.getCategories().getBody();
    }
}