package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientosRequestDto {
    private Integer detalleComprobanteSalidaId;
    private Integer detalleNotaIngresoId;
    private String tipo;
    private Integer stockActual;
}
