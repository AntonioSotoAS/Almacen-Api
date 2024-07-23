package com.almacen.controller;

import com.almacen.model.dto.ProductoRequestDto;
import com.almacen.model.dto.ProductoResponseDto;
import com.almacen.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponseDto> createProducto(@RequestBody ProductoRequestDto productoRequestDto) {
        ProductoResponseDto createdProducto = productoService.createProducto(productoRequestDto);
        return ResponseEntity.ok(createdProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> updateProducto(@PathVariable Integer id, @RequestBody ProductoRequestDto productoRequestDto) {
        ProductoResponseDto updatedProducto = productoService.updateProducto(id, productoRequestDto);
        return ResponseEntity.ok(updatedProducto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> getProductoById(@PathVariable Integer id) {
        ProductoResponseDto producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> getAllProductos() {
        List<ProductoResponseDto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
