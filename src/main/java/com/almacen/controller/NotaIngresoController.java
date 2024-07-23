package com.almacen.controller;

import com.almacen.model.dto.NotaIngresoRequestDto;
import com.almacen.model.dto.NotaIngresoResponseDto;
import com.almacen.service.NotaIngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas-ingreso")
public class NotaIngresoController {

    @Autowired
    private NotaIngresoService notaIngresoService;

    @PostMapping
    public ResponseEntity<NotaIngresoResponseDto> createNotaIngreso(@RequestBody NotaIngresoRequestDto notaIngresoRequestDto) {
        NotaIngresoResponseDto createdNotaIngreso = notaIngresoService.createNotaIngreso(notaIngresoRequestDto);
        return ResponseEntity.ok(createdNotaIngreso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaIngresoResponseDto> updateNotaIngreso(@PathVariable Integer id, @RequestBody NotaIngresoRequestDto notaIngresoRequestDto) {
        NotaIngresoResponseDto updatedNotaIngreso = notaIngresoService.updateNotaIngreso(id, notaIngresoRequestDto);
        return ResponseEntity.ok(updatedNotaIngreso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaIngresoResponseDto> getNotaIngresoById(@PathVariable Integer id) {
        NotaIngresoResponseDto notaIngreso = notaIngresoService.getNotaIngresoById(id);
        return ResponseEntity.ok(notaIngreso);
    }

    @GetMapping
    public ResponseEntity<List<NotaIngresoResponseDto>> getAllNotasIngreso() {
        List<NotaIngresoResponseDto> notasIngreso = notaIngresoService.getAllNotasIngreso();
        return ResponseEntity.ok(notasIngreso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotaIngreso(@PathVariable Integer id) {
        notaIngresoService.deleteNotaIngreso(id);
        return ResponseEntity.noContent().build();
    }
}
