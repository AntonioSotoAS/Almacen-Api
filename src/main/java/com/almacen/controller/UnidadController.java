package com.almacen.controller;

import com.almacen.model.dto.UnidadRequestDto;
import com.almacen.model.dto.UnidadResponseDto;
import com.almacen.service.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/unidades")
public class UnidadController {

    @Autowired
    private UnidadService unidadService;

    @PostMapping
    public ResponseEntity<UnidadResponseDto> createUnidad(@RequestBody UnidadRequestDto unidadRequestDto) {
        UnidadResponseDto unidadResponseDto = unidadService.createUnidad(unidadRequestDto);
        return ResponseEntity.ok(unidadResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadResponseDto> updateUnidad(@PathVariable Integer id, @RequestBody UnidadRequestDto unidadRequestDto) {
        UnidadResponseDto unidadResponseDto = unidadService.updateUnidad(id, unidadRequestDto);
        return ResponseEntity.ok(unidadResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadResponseDto> getUnidadById(@PathVariable Integer id) {
        UnidadResponseDto unidadResponseDto = unidadService.getUnidadById(id);
        return ResponseEntity.ok(unidadResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UnidadResponseDto>> getAllUnidades() {
        List<UnidadResponseDto> unidades = unidadService.getAllUnidades();
        return ResponseEntity.ok(unidades);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnidad(@PathVariable Integer id) {
        unidadService.deleteUnidad(id);
        return ResponseEntity.noContent().build();
    }
}
