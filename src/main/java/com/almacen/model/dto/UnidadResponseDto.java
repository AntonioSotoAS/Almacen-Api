package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadResponseDto {
    private Integer idUnidad;
    private String jefeUnidad;
    private String nombreUnidad;
    private String estado;
    private Integer idUsuario;
    private String usuarioNombre;
    private Integer idDependencia;
    private String dependenciaNombre;
    private Integer idProveedor;
    private String proveedorNombre;
}
