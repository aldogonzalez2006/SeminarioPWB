package com.product.api.service;

import java.util.List;
/**
 * Tambien actualizamos esta clase, agregamos librerias necesarias
 */
import com.product.exception.DBAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.repository.RepoCategory;
import com.product.api.entity.Category;
import com.product.exception.ApiException;

@Service
public class SvcCategoryImp implements SvcCategory {

    @Autowired
    private RepoCategory repo;

//    @Override
//    public ResponseEntity<List<Category>> getCategories(){
//        try{
//            return new ResponseEntity<>(repo.gCategories(), HttpStatus.OK); //repo.findAll();
//        } catch (ApiException e){
//            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }

    /**
     * @return
     */
    @Override
    public List<Category> findAll() {
        try {
            return repo.getAll();
        }catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
        //return List.of();
    }

    /**
     * @return 
     */
    @Override
    public List<Category> findActive() {
        try {
            return repo.findActive();
        }catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * @param in 
     */
    @Override
    public void create(DtoCategoryIn in) {
        try {
            repo.create(in.getCategory(), in.getTag());
        }catch (DataAccessException e) {
            if (e.getLocalizedMessage().contains("ux_region"))
                throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoría ya está registrado");
            if (e.getLocalizedMessage().contains("ux_tag"))
                throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoría ya está registrado");
        }
    }

    /**
     * @param in 
     * @param id
     */
    @Override
    public void update(DtoCategoryIn in, Integer id) {
        try {
            repo.update(id, in.getCategory(), in.getTag());
        }catch (DataAccessException e) {
            if(repo.findById(id).isEmpty())
                throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
        }
    }

    /**
     * @param id 
     */
    @Override
    public void enable(Integer id) {
        try {
            validateId(id);
            repo.enable(id);
        }catch (DataAccessException e) {
            if(repo.findById(id).isEmpty())
                throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
        }
    }

    /**
     * @param id 
     */
    @Override
    public void disable(Integer id) {
        try {
            validateId(id);
            repo.disable(id); // Asegúrate de que este método exista en RepoCategory
        }catch (DataAccessException e) {
            if(repo.findById(id).isEmpty())
                throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
        }
    }

    private void validateId(Integer id) {
        if(repo.findById(id).isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
    }
}