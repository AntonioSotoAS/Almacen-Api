package com.almacen.model.dto;

import com.almacen.model.entity.Producto.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class ProductoResponseDto {
    private String id_producto;
    private String marca;
    private String categoria;
    private String unidadMedida;
    private String cod;
    private String descripcion;
    private BigDecimal precio;
    private Estado estado;

    public ProductoResponseDto(String cod, String descripcion, String nombreCategoria, String nombreMarca,  BigDecimal precio, Estado estado, String unidadMedida,String id_producto) {
        this.cod = cod;
        this.descripcion = descripcion;
        this.categoria = nombreCategoria;
        this.marca = nombreMarca;
        this.precio = precio;
        this.estado = estado;
        this.unidadMedida = unidadMedida;
        this.id_producto = id_producto;
    }

    public ProductoResponseDto() {
    }

    public ProductoResponseDto(String id_producto, String marca, String categoria, String unidadMedida, String cod, String descripcion, BigDecimal precio, Estado estado) {
        this.id_producto = id_producto;
        this.marca = marca;
        this.categoria = categoria;
        this.unidadMedida = unidadMedida;
        this.cod = cod;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }


}
