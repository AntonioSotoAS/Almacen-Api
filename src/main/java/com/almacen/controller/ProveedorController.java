package com.almacen.controller;

import com.almacen.model.dto.ProveedorRequestDto;
import com.almacen.model.dto.ProveedorResponseDto;
import com.almacen.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<ProveedorResponseDto> createProveedor(@RequestBody ProveedorRequestDto proveedorRequestDto) {
        ProveedorResponseDto proveedorResponseDto = proveedorService.createProveedor(proveedorRequestDto);
        return ResponseEntity.ok(proveedorResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDto> updateProveedor(@PathVariable Integer id, @RequestBody ProveedorRequestDto proveedorRequestDto) {
        ProveedorResponseDto proveedorResponseDto = proveedorService.updateProveedor(id, proveedorRequestDto);
        return ResponseEntity.ok(proveedorResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDto> getProveedorById(@PathVariable Integer id) {
        ProveedorResponseDto proveedorResponseDto = proveedorService.getProveedorById(id);
        return ResponseEntity.ok(proveedorResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponseDto>> getAllProveedores() {
        List<ProveedorResponseDto> proveedores = proveedorService.getAllProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Integer id) {
        proveedorService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
