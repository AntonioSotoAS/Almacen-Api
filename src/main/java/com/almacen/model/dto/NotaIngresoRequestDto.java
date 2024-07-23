package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class NotaIngresoRequestDto {
    private Date fecha;
    private String tipoMov;
    private Integer idProveedor;
    private List<DetalleNotaIngresoRequestDto> detallesNotaIngreso;
}
