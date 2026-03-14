package com.product.api.service;

import java.util.List;
/**
 * Tambien actualizamos esta clase, agregamos librerias necesarias
 */
import com.product.api.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.product.api.repository.RepoCategory;
import com.product.api.entity.Category;

@Service
public class SvcCategoryImp implements SvcCategory {

    @Autowired
    private RepoCategory repo;

    @Override
    public ResponseEntity<List<Category>> getCategories(){
        try{
            return new ResponseEntity<>(repo.gCategories(), HttpStatus.OK); //repo.findAll();
        } catch (ApiException e){
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}