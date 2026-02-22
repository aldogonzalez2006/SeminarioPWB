package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CtrlProduct {

    @GetMapping("/category")
    public Category[] getCategories() {

        Category c1 = new Category(1, "Lentes", "Lts", 1);
        Category c2 = new Category(2, "Relojes", "Rljs", 1);

        return new Category[]{c1, c2};
    }
}