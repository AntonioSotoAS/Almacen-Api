package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotaIngresoResponseDto {
    private Integer idNotaIngreso;
    private String dctoRefe;
    private Date fecha;
    private String tipoMov;
    private String ocompra;
    private String nguia;
    private Integer idProveedor;
    private String proveedorNombre;
}
