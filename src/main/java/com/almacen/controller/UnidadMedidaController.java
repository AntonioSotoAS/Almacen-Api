package com.almacen.controller;

import com.almacen.model.dto.UnidadMedidaCreateOrUpdateDto;
import com.almacen.model.dto.UnidadMedidaResponseDto;
import com.almacen.service.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/unidad-medidas")
public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    @PostMapping
    public ResponseEntity<UnidadMedidaResponseDto> createUnidadMedida(@RequestBody UnidadMedidaCreateOrUpdateDto unidadMedidaCreateOrUpdateDto) {
        UnidadMedidaResponseDto createdUnidadMedida = unidadMedidaService.createUnidadMedida(unidadMedidaCreateOrUpdateDto);
        return ResponseEntity.ok(createdUnidadMedida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponseDto> updateUnidadMedida(@PathVariable Integer id, @RequestBody UnidadMedidaCreateOrUpdateDto unidadMedidaCreateOrUpdateDto) {
        UnidadMedidaResponseDto updatedUnidadMedida = unidadMedidaService.updateUnidadMedida(id, unidadMedidaCreateOrUpdateDto);
        return ResponseEntity.ok(updatedUnidadMedida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponseDto> getUnidadMedidaById(@PathVariable Integer id) {
        UnidadMedidaResponseDto unidadMedida = unidadMedidaService.getUnidadMedidaById(id);
        return ResponseEntity.ok(unidadMedida);
    }

    @GetMapping
    public ResponseEntity<List<UnidadMedidaResponseDto>> getAllUnidadMedidas() {
        List<UnidadMedidaResponseDto> unidadMedidas = unidadMedidaService.getAllUnidadMedidas();
        return ResponseEntity.ok(unidadMedidas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnidadMedida(@PathVariable Integer id) {
        unidadMedidaService.deleteUnidadMedida(id);
        return ResponseEntity.noContent().build();
    }
}
