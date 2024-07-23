package com.almacen.service;

import com.almacen.model.dto.ProductoRequestDto;
import com.almacen.model.dto.ProductoResponseDto;

import java.util.List;

public interface ProductoService {
    ProductoResponseDto createProducto(ProductoRequestDto productoRequestDto);
    ProductoResponseDto updateProducto(Integer id, ProductoRequestDto productoRequestDto);
    ProductoResponseDto getProductoById(Integer id);
    List<ProductoResponseDto> getAllProductos();
    void deleteProducto(Integer id);
}
