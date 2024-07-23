package com.almacen.controller;

import com.almacen.model.dto.ComprobanteSalidaRequestDto;
import com.almacen.model.dto.ComprobanteSalidaResponseDto;
import com.almacen.service.ComprobanteSalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comprobantes-salida")
public class ComprobanteSalidaController {

    @Autowired
    private ComprobanteSalidaService comprobanteSalidaService;

    @PostMapping
    public ResponseEntity<ComprobanteSalidaResponseDto> createComprobanteSalida(@RequestBody ComprobanteSalidaRequestDto comprobanteSalidaRequestDto) {
        ComprobanteSalidaResponseDto createdComprobanteSalida = comprobanteSalidaService.createComprobanteSalida(comprobanteSalidaRequestDto);
        return ResponseEntity.ok(createdComprobanteSalida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComprobanteSalidaResponseDto> updateComprobanteSalida(@PathVariable Integer id, @RequestBody ComprobanteSalidaRequestDto comprobanteSalidaRequestDto) {
        ComprobanteSalidaResponseDto updatedComprobanteSalida = comprobanteSalidaService.updateComprobanteSalida(id, comprobanteSalidaRequestDto);
        return ResponseEntity.ok(updatedComprobanteSalida);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteSalidaResponseDto> getComprobanteSalidaById(@PathVariable Integer id) {
        ComprobanteSalidaResponseDto comprobanteSalida = comprobanteSalidaService.getComprobanteSalidaById(id);
        return ResponseEntity.ok(comprobanteSalida);
    }

    @GetMapping
    public ResponseEntity<List<ComprobanteSalidaResponseDto>> getAllComprobantesSalida() {
        List<ComprobanteSalidaResponseDto> comprobantesSalida = comprobanteSalidaService.getAllComprobantesSalida();
        return ResponseEntity.ok(comprobantesSalida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComprobanteSalida(@PathVariable Integer id) {
        comprobanteSalidaService.deleteComprobanteSalida(id);
        return ResponseEntity.noContent().build();
    }
}
