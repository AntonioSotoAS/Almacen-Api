package com.almacen.service;

import com.almacen.model.dto.CategoriaCreateOrUpdateDto;
import com.almacen.model.dto.CategoriaResponseDto;

import java.util.List;

public interface CategoriaService {
    CategoriaResponseDto createCategoria(CategoriaCreateOrUpdateDto categoriaCreateOrUpdateDto);

    CategoriaResponseDto updateCategoria(Integer id, CategoriaCreateOrUpdateDto categoriaCreateOrUpdateDto);

    CategoriaResponseDto getCategoriaById(Integer id);

    List<CategoriaResponseDto> getAllCategorias();

    void deleteCategoria(Integer id);
}
