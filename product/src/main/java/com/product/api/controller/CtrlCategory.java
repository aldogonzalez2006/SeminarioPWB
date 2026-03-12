package com.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.product.api.service.SvcCategory;
import com.product.api.entity.Category;

@RestController
public class CtrlCategory {

    @Autowired
    private SvcCategory svc;

    @GetMapping("/category")
    public List<Category> getCategories() {
        return svc.getCategories();
    }
}