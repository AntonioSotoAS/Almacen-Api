package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleNotaIngresoRequestDto {
    private Integer idNotaIngreso;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal igv;
    private BigDecimal total;
    private Integer idMovimiento;
    private Integer idProducto;
}
