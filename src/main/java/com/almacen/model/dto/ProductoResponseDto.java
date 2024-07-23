package com.almacen.model.dto;

import com.almacen.model.entity.Producto.Estado;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoResponseDto {
    private Integer id_producto;
    private String marca;
    private String categoria;
    private String unidadMedida;
    private String cod;
    private String descripcion;
    private BigDecimal precio;
    private Estado estado;
}
