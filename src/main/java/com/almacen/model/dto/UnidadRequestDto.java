package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadRequestDto {
    private String jefeUnidad;
    private String nombreUnidad;
    private String estado;
    private Integer idUsuario;
    private Integer idDependencia;
    private Integer idProveedor;
}
