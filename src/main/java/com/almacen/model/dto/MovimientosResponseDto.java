package com.almacen.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovimientosResponseDto {
    //private Integer idMovimiento;
    //private Integer detalleComprobanteSalidaId;
    //private Integer detalleNotaIngresoId;
    //private String tipo;
    //private Integer stockActual;

    private Integer idMovimiento;
    private Integer detalleComprobanteSalidaId;
    private Integer detalleNotaIngresoId;
    private String tipo;
    private Integer stockActual;
    private LocalDate fecha;
    private String descripcion;
    private BigDecimal costoUnitario;
    private Integer entrada;
    private Integer salida;
    private Integer stock;

}
