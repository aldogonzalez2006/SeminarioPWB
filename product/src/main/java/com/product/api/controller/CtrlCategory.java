package com.product.api.controller;

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

// nuevos paquetes swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Operaciones relacionadas con las categorías de productos")
public class CtrlCategory {

    @Autowired
    private SvcCategory svc;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(svc.findAll());
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Category>> findActive(){
        return ResponseEntity.ok(svc.findActive());
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody DtoCategoryIn in){
        svc.create(in);
        return ResponseEntity.ok("Categoría registrada");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody DtoCategoryIn in, 
            @PathVariable Integer id){
        svc.update(in, id);
        return ResponseEntity.ok("Categoría actualizada");
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<String> enable(@PathVariable Integer id){
        svc.enable(id);
        return ResponseEntity.ok("Categoría activada");
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<String> disable(@PathVariable Integer id){
        svc.disable(id);
        return ResponseEntity.ok("Categoría desactivada");
    }
    
}