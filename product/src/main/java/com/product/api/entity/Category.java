package com.product.api.entity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
/**
 * El objetivo de esta practica es que el usuario pueda crear, eliminar e imprimir desde la consola
 * 
 */

@Entity
@Table(name="category")
public class Category {
/**
 * Agregamos las siguientes notaciones para tener nuestro CRUD completo para que en el JSON de 
 * Postman se vean como lo indicamos
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("category_id")
    @Column (name = "category_id")
    private int category_id;

    @JsonProperty("category")
    @Column (name = "category")
    private String category;

    @JsonProperty("tag")
    @Column(name = "tag")
    private String tag;

    @JsonProperty("status")
    @Column(name = "status")
    private int status;

    public Category() {
    }
/**
 * Cambiamos el int por Integer ya que su principal diferencia es que Integer se inicializa en null como lo necesitamos
 * mientras que int en 0.
 */
    public Category(Integer id, String category, String tag, Integer status) {
        super();
        this.category_id = id;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    @Override
    public String toString(){
        return "["+getCategory_id()+", "+getCategory()+","
                  +getTag()+ ", "+getStatus()+"]";
    }

    public void setCategory_id(Integer category_id) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}