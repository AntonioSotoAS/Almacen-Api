package com.almacen.service;

import com.almacen.model.dto.UnidadRequestDto;
import com.almacen.model.dto.UnidadResponseDto;

import java.util.List;

public interface UnidadService {
    UnidadResponseDto createUnidad(UnidadRequestDto unidadRequestDto);
    UnidadResponseDto updateUnidad(Integer id, UnidadRequestDto unidadRequestDto);
    UnidadResponseDto getUnidadById(Integer id);
    List<UnidadResponseDto> getAllUnidades();
    void deleteUnidad(Integer id);
}
