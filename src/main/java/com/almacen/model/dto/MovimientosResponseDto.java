package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovimientosResponseDto {
    private Integer idMovimiento;
    private Integer detalleComprobanteSalidaId;
    private Integer detalleNotaIngresoId;
    private String tipo;
    private Integer stockActual;
}
