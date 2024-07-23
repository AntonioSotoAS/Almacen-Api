package com.almacen.service;

import com.almacen.model.dto.DetalleNotaIngresoRequestDto;
import com.almacen.model.dto.DetalleNotaIngresoResponseDto;

import java.util.List;

public interface DetalleNotaIngresoService {
    DetalleNotaIngresoResponseDto createDetalleNotaIngreso(DetalleNotaIngresoRequestDto detalleNotaIngresoRequestDto);
    DetalleNotaIngresoResponseDto updateDetalleNotaIngreso(Integer id, DetalleNotaIngresoRequestDto detalleNotaIngresoRequestDto);
    DetalleNotaIngresoResponseDto getDetalleNotaIngresoById(Integer id);
    List<DetalleNotaIngresoResponseDto> getAllDetallesNotaIngreso();
    void deleteDetalleNotaIngreso(Integer id);
}
