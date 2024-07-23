package com.almacen.service;

import com.almacen.model.dto.DependenciaRequestDto;
import com.almacen.model.dto.DependenciaResponseDto;

import java.util.List;

public interface DependenciaService {
    DependenciaResponseDto createDependencia(DependenciaRequestDto dependenciaRequestDto);
    DependenciaResponseDto updateDependencia(Integer id, DependenciaRequestDto dependenciaRequestDto);
    DependenciaResponseDto getDependenciaById(Integer id);
    List<DependenciaResponseDto> getAllDependencias();
    void deleteDependencia(Integer id);
}
