package com.product.api.controller;
/**
 * Actualizamos muchas librerias tomadas del repo del profe
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.product.api.dto.DtoCategoryIn;
import com.product.api.service.SvcCategory;
import com.product.api.entity.Category;

import jakarta.validation.Valid;

/**
 * Endpoint para manejar las peticiones de categorías.
 */
@RestController
@RequestMapping ("/category")
public class CtrlCategory {

    @Autowired
    private SvcCategory svc;
/**
 * Actualizamos la clase segun los cambios que le hicimos a ctrlCategory y asi el usuario pueda 
 * manejar los endpoints, reemplazamos lo que era List<Categories> getCategories
 */
    @GetMapping
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok(svc.findAll());
	}
    
    @GetMapping("/active")
    public ResponseEntity<List<Category>>findActive(){
        return ResponseEntity.ok(svc.findActive());
    }
   @PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody DtoCategoryIn in){
		svc.create(in);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody DtoCategoryIn in, 
			@PathVariable Integer id){
		svc.update(in, id);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{id}/enable")
	public ResponseEntity<Void> enable(@PathVariable Integer id){
		svc.enable(id);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{id}/disable")
	public ResponseEntity<Void> disable(@PathVariable Integer id){
		svc.disable(id);
		return ResponseEntity.ok().build();
	}
	
}