package com.almacen.model.dto;

import com.almacen.model.entity.Producto.Estado;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoRequestDto {
    private Integer id_marca;
    private Integer id_categoria;
    private Integer id_unimed;
    private String descripcion;
    private BigDecimal precio;
    private Estado estado;
}
