package com.almacen.service;

import com.almacen.model.dto.UnidadMedidaCreateOrUpdateDto;
import com.almacen.model.dto.UnidadMedidaResponseDto;

import java.util.List;

public interface UnidadMedidaService {
    UnidadMedidaResponseDto createUnidadMedida(UnidadMedidaCreateOrUpdateDto unidadMedidaCreateOrUpdateDto);
    UnidadMedidaResponseDto updateUnidadMedida(Integer id, UnidadMedidaCreateOrUpdateDto unidadMedidaCreateOrUpdateDto);
    UnidadMedidaResponseDto getUnidadMedidaById(Integer id);
    List<UnidadMedidaResponseDto> getAllUnidadMedidas();
    void deleteUnidadMedida(Integer id);
}
