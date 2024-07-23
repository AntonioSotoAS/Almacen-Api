package com.almacen.controller;

import com.almacen.model.dto.MarcaCreateOrUpdateDto;
import com.almacen.model.dto.MarcaResponseDto;
import com.almacen.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaResponseDto> createMarca(@RequestBody MarcaCreateOrUpdateDto marcaCreateOrUpdateDto) {
        MarcaResponseDto createdMarca = marcaService.createMarca(marcaCreateOrUpdateDto);
        return ResponseEntity.ok(createdMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDto> updateMarca(@PathVariable Integer id, @RequestBody MarcaCreateOrUpdateDto marcaCreateOrUpdateDto) {
        MarcaResponseDto updatedMarca = marcaService.updateMarca(id, marcaCreateOrUpdateDto);
        return ResponseEntity.ok(updatedMarca);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDto> getMarcaById(@PathVariable Integer id) {
        MarcaResponseDto marca = marcaService.getMarcaById(id);
        return ResponseEntity.ok(marca);
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponseDto>> getAllMarcas() {
        List<MarcaResponseDto> marcas = marcaService.getAllMarcas();
        return ResponseEntity.ok(marcas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Integer id) {
        marcaService.deleteMarca(id);
        return ResponseEntity.noContent().build();
    }
}
