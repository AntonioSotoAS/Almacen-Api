package com.almacen.controller;

import com.almacen.model.dto.MovimientosRequestDto;
import com.almacen.model.dto.MovimientosResponseDto;
import com.almacen.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientosController {

    @Autowired
    private MovimientosService movimientosService;

    @PostMapping
    public ResponseEntity<MovimientosResponseDto> createMovimiento(@RequestBody MovimientosRequestDto movimientosRequestDto) {
        MovimientosResponseDto movimientosResponseDto = movimientosService.createMovimiento(movimientosRequestDto);
        return ResponseEntity.ok(movimientosResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientosResponseDto> updateMovimiento(@PathVariable Integer id, @RequestBody MovimientosRequestDto movimientosRequestDto) {
        MovimientosResponseDto movimientosResponseDto = movimientosService.updateMovimiento(id, movimientosRequestDto);
        return ResponseEntity.ok(movimientosResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientosResponseDto> getMovimientoById(@PathVariable Integer id) {
        MovimientosResponseDto movimientosResponseDto = movimientosService.getMovimientoById(id);
        return ResponseEntity.ok(movimientosResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<MovimientosResponseDto>> getAllMovimientos() {
        List<MovimientosResponseDto> movimientosResponseDtos = movimientosService.getAllMovimientos();
        return ResponseEntity.ok(movimientosResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Integer id) {
        movimientosService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stock/{productoId}")
    public ResponseEntity<Integer> getStockActual(@PathVariable Integer productoId) {
        Integer stockActual = movimientosService.getStockActualByProductoId(productoId);
        return ResponseEntity.ok(stockActual);
    }
}
