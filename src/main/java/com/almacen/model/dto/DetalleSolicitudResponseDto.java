package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleSolicitudResponseDto {
    private Integer id;
    private String productoNombre;
    private Integer cantidad;
    private BigDecimal total;
}
