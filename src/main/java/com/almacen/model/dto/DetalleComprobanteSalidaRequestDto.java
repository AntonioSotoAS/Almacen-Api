package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleComprobanteSalidaRequestDto {
    private Integer cantidad;
    private Integer idProducto;
    private Integer idComprobanteSalida;
}
