package com.almacen.service;

import com.almacen.model.dto.MovimientosRequestDto;
import com.almacen.model.dto.MovimientosResponseDto;

import java.util.List;

public interface MovimientosService {
    MovimientosResponseDto createMovimiento(MovimientosRequestDto movimientosRequestDto);
    MovimientosResponseDto updateMovimiento(Integer id, MovimientosRequestDto movimientosRequestDto);
    MovimientosResponseDto getMovimientoById(Integer id);
    List<MovimientosResponseDto> getAllMovimientos();
    void deleteMovimiento(Integer id);
    Integer getStockActualByProductoId(Integer productoId);
}
