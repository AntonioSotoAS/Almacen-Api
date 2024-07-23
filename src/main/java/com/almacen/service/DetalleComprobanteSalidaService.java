package com.almacen.service;

import com.almacen.model.dto.DetalleComprobanteSalidaRequestDto;
import com.almacen.model.dto.DetalleComprobanteSalidaResponseDto;

import java.util.List;

public interface DetalleComprobanteSalidaService {
    DetalleComprobanteSalidaResponseDto createDetalleComprobanteSalida(DetalleComprobanteSalidaRequestDto detalleComprobanteSalidaRequestDto);
    DetalleComprobanteSalidaResponseDto updateDetalleComprobanteSalida(Integer id, DetalleComprobanteSalidaRequestDto detalleComprobanteSalidaRequestDto);
    DetalleComprobanteSalidaResponseDto getDetalleComprobanteSalidaById(Integer id);
    List<DetalleComprobanteSalidaResponseDto> getAllDetallesComprobanteSalida();
    void deleteDetalleComprobanteSalida(Integer id);
}
