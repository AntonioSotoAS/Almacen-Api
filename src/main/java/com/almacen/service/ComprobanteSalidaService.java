package com.almacen.service;

import com.almacen.model.dto.ComprobanteSalidaRequestDto;
import com.almacen.model.dto.ComprobanteSalidaResponseDto;

import java.util.List;

public interface ComprobanteSalidaService {
    ComprobanteSalidaResponseDto createComprobanteSalida(ComprobanteSalidaRequestDto comprobanteSalidaRequestDto);
    ComprobanteSalidaResponseDto updateComprobanteSalida(Integer id, ComprobanteSalidaRequestDto comprobanteSalidaRequestDto);
    ComprobanteSalidaResponseDto getComprobanteSalidaById(Integer id);
    List<ComprobanteSalidaResponseDto> getAllComprobantesSalida();
    void deleteComprobanteSalida(Integer id);
}
