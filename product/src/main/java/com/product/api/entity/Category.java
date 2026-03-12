package com.product.api.entity;
import jakarta.persistence.*;
/**
 * El objetivo de esta practica es que el usuario pueda crear, eliminar e imprimir desde la consola
 * 
 */
@Entity
@Table(name="category")

public class Category {
    @Id
    private int category_id;
    private String category;
    private String tag;
    private int status;

    public Category() {
    }

    public Category(int id, String category, String tag) {
        this.category_id = id;
        this.category = category;
        this.tag = tag;
        this.status = 1;
    }

    public int getCategory_id() {
        return category_id;
    }

    @Override
    public String toString(){
        return "["+getCategory_id()+", "+getCategory()+","
                  +getTag()+ ", "+getStatus()+"]";
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}