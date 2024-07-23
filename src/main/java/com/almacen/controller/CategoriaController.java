package com.almacen.controller;

import com.almacen.model.dto.CategoriaCreateOrUpdateDto;
import com.almacen.model.dto.CategoriaResponseDto;
import com.almacen.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> createCategoria(@RequestBody CategoriaCreateOrUpdateDto categoriaCreateOrUpdateDto) {
        CategoriaResponseDto createdCategoria = categoriaService.createCategoria(categoriaCreateOrUpdateDto);
        return ResponseEntity.ok(createdCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> updateCategoria(@PathVariable Integer id, @RequestBody CategoriaCreateOrUpdateDto categoriaCreateOrUpdateDto) {
        CategoriaResponseDto updatedCategoria = categoriaService.updateCategoria(id, categoriaCreateOrUpdateDto);
        return ResponseEntity.ok(updatedCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> getCategoriaById(@PathVariable Integer id) {
        CategoriaResponseDto categoria = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> getAllCategorias() {
        List<CategoriaResponseDto> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
