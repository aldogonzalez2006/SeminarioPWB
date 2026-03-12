package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esta clase implementa las APIs relacionadas con la entidad Category
 * @author AldoJael
 * @author Ihebel
 */
@RestController
public class CtrlProduct {

    /**
     * Retorna un Json con la lista de categorias disponibles, en este caso
     * las creadas por el mismo método
     * @author AldoJael
     * @author Ihebel
     * @return Json con la lista de categorias
     */
    @GetMapping("/category")
    public Category[] getCategories() {

        Category c1 = new Category(1, "Lentes", "Lts");
        Category c2 = new Category(2, "Relojes", "Rljs");

        return new Category[]{c1, c2};
    }
}