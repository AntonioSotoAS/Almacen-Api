package com.almacen.service;

import com.almacen.model.dto.MarcaCreateOrUpdateDto;
import com.almacen.model.dto.MarcaResponseDto;

import java.util.List;

public interface MarcaService {
    MarcaResponseDto createMarca(MarcaCreateOrUpdateDto marcaCreateOrUpdateDto);
    MarcaResponseDto updateMarca(Integer id, MarcaCreateOrUpdateDto marcaCreateOrUpdateDto);
    MarcaResponseDto getMarcaById(Integer id);
    List<MarcaResponseDto> getAllMarcas();
    void deleteMarca(Integer id);
}
