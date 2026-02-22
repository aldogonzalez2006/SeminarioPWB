package com.product;

/**
 * Clase Category utilizada para exponer datos mediante API REST.
 */
public class Category {

    private int id;
    private String category;
    private String tag;
    private int status;

    // Constructor vacío (buena práctica en Spring)
    public Category() {
    }

    // Constructor principal
    public Category(int id, String category, String tag, int status) {
        this.id = id;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }

    // Constructor alternativo
    public Category(int id, String category, String tag) {
        this.id = id;
        this.category = category;
        this.tag = tag;
        this.status = 1;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTag() {
        return tag;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}