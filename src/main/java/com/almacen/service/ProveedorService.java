package com.almacen.service;

import com.almacen.model.dto.ProveedorRequestDto;
import com.almacen.model.dto.ProveedorResponseDto;

import java.util.List;

public interface ProveedorService {
    ProveedorResponseDto createProveedor(ProveedorRequestDto proveedorRequestDto);
    ProveedorResponseDto updateProveedor(Integer id, ProveedorRequestDto proveedorRequestDto);
    ProveedorResponseDto getProveedorById(Integer id);
    List<ProveedorResponseDto> getAllProveedores();
    void deleteProveedor(Integer id);
}
