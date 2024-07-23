package com.almacen.controller;

import com.almacen.model.dto.DependenciaRequestDto;
import com.almacen.model.dto.DependenciaResponseDto;
import com.almacen.service.DependenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dependencias")
public class DependenciaController {

    @Autowired
    private DependenciaService dependenciaService;

    @PostMapping
    public ResponseEntity<DependenciaResponseDto> createDependencia(@RequestBody DependenciaRequestDto dependenciaRequestDto) {
        DependenciaResponseDto createdDependencia = dependenciaService.createDependencia(dependenciaRequestDto);
        return ResponseEntity.ok(createdDependencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DependenciaResponseDto> updateDependencia(@PathVariable Integer id, @RequestBody DependenciaRequestDto dependenciaRequestDto) {
        DependenciaResponseDto updatedDependencia = dependenciaService.updateDependencia(id, dependenciaRequestDto);
        return ResponseEntity.ok(updatedDependencia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DependenciaResponseDto> getDependenciaById(@PathVariable Integer id) {
        DependenciaResponseDto dependencia = dependenciaService.getDependenciaById(id);
        return ResponseEntity.ok(dependencia);
    }

    @GetMapping
    public ResponseEntity<List<DependenciaResponseDto>> getAllDependencias() {
        List<DependenciaResponseDto> dependencias = dependenciaService.getAllDependencias();
        return ResponseEntity.ok(dependencias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDependencia(@PathVariable Integer id) {
        dependenciaService.deleteDependencia(id);
        return ResponseEntity.noContent().build();
    }
}
