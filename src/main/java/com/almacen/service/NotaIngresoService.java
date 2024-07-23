package com.almacen.service;

import com.almacen.model.dto.NotaIngresoRequestDto;
import com.almacen.model.dto.NotaIngresoResponseDto;

import java.util.List;

public interface NotaIngresoService {
    NotaIngresoResponseDto createNotaIngreso(NotaIngresoRequestDto notaIngresoRequestDto);
    NotaIngresoResponseDto updateNotaIngreso(Integer id, NotaIngresoRequestDto notaIngresoRequestDto);
    NotaIngresoResponseDto getNotaIngresoById(Integer id);
    List<NotaIngresoResponseDto> getAllNotasIngreso();
    void deleteNotaIngreso(Integer id);
}
