package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProveedorResponseDto {
    private Integer idProveedor;
    private String nombreProv;
    private String estado;
    private Set<UnidadResponseDto> unidades;
    private Set<NotaIngresoResponseDto> notasIngreso;
}