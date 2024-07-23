package com.almacen.controller;

import com.almacen.model.dto.DetalleNotaIngresoRequestDto;
import com.almacen.model.dto.DetalleNotaIngresoResponseDto;
import com.almacen.service.DetalleNotaIngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles-nota-ingreso")
public class DetalleNotaIngresoController {

    @Autowired
    private DetalleNotaIngresoService detalleNotaIngresoService;

    @PostMapping
    public ResponseEntity<DetalleNotaIngresoResponseDto> createDetalleNotaIngreso(@RequestBody DetalleNotaIngresoRequestDto detalleNotaIngresoRequestDto) {
        DetalleNotaIngresoResponseDto createdDetalleNotaIngreso = detalleNotaIngresoService.createDetalleNotaIngreso(detalleNotaIngresoRequestDto);
        return ResponseEntity.ok(createdDetalleNotaIngreso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleNotaIngresoResponseDto> updateDetalleNotaIngreso(@PathVariable Integer id, @RequestBody DetalleNotaIngresoRequestDto detalleNotaIngresoRequestDto) {
        DetalleNotaIngresoResponseDto updatedDetalleNotaIngreso = detalleNotaIngresoService.updateDetalleNotaIngreso(id, detalleNotaIngresoRequestDto);
        return ResponseEntity.ok(updatedDetalleNotaIngreso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleNotaIngresoResponseDto> getDetalleNotaIngresoById(@PathVariable Integer id) {
        DetalleNotaIngresoResponseDto detalleNotaIngreso = detalleNotaIngresoService.getDetalleNotaIngresoById(id);
        return ResponseEntity.ok(detalleNotaIngreso);
    }

    @GetMapping
    public ResponseEntity<List<DetalleNotaIngresoResponseDto>> getAllDetallesNotaIngreso() {
        List<DetalleNotaIngresoResponseDto> detallesNotaIngreso = detalleNotaIngresoService.getAllDetallesNotaIngreso();
        return ResponseEntity.ok(detallesNotaIngreso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleNotaIngreso(@PathVariable Integer id) {
        detalleNotaIngresoService.deleteDetalleNotaIngreso(id);
        return ResponseEntity.noContent().build();
    }
}
