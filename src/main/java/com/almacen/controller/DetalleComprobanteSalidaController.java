package com.almacen.controller;

import com.almacen.model.dto.DetalleComprobanteSalidaRequestDto;
import com.almacen.model.dto.DetalleComprobanteSalidaResponseDto;
import com.almacen.service.DetalleComprobanteSalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles-comprobante-salida")
public class DetalleComprobanteSalidaController {

    @Autowired
    private DetalleComprobanteSalidaService detalleComprobanteSalidaService;

    @PostMapping
    public ResponseEntity<DetalleComprobanteSalidaResponseDto> createDetalleComprobanteSalida(@RequestBody DetalleComprobanteSalidaRequestDto detalleComprobanteSalidaRequestDto) {
        DetalleComprobanteSalidaResponseDto createdDetalleComprobanteSalida = detalleComprobanteSalidaService.createDetalleComprobanteSalida(detalleComprobanteSalidaRequestDto);
        return ResponseEntity.ok(createdDetalleComprobanteSalida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleComprobanteSalidaResponseDto> updateDetalleComprobanteSalida(@PathVariable Integer id, @RequestBody DetalleComprobanteSalidaRequestDto detalleComprobanteSalidaRequestDto) {
        DetalleComprobanteSalidaResponseDto updatedDetalleComprobanteSalida = detalleComprobanteSalidaService.updateDetalleComprobanteSalida(id, detalleComprobanteSalidaRequestDto);
        return ResponseEntity.ok(updatedDetalleComprobanteSalida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleComprobanteSalidaResponseDto> getDetalleComprobanteSalidaById(@PathVariable Integer id) {
        DetalleComprobanteSalidaResponseDto detalleComprobanteSalida = detalleComprobanteSalidaService.getDetalleComprobanteSalidaById(id);
        return ResponseEntity.ok(detalleComprobanteSalida);
    }

    @GetMapping
    public ResponseEntity<List<DetalleComprobanteSalidaResponseDto>> getAllDetallesComprobanteSalida() {
        List<DetalleComprobanteSalidaResponseDto> detallesComprobanteSalida = detalleComprobanteSalidaService.getAllDetallesComprobanteSalida();
        return ResponseEntity.ok(detallesComprobanteSalida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleComprobanteSalida(@PathVariable Integer id) {
        detalleComprobanteSalidaService.deleteDetalleComprobanteSalida(id);
        return ResponseEntity.noContent().build();
    }
}
