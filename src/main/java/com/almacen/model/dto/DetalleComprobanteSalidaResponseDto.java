package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleComprobanteSalidaResponseDto {
    private Integer idDetalleCompSalida;
    private Integer cantidad;
    private String productoDescripcion;
    private Integer idComprobanteSalida;
}
