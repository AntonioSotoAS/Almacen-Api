package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DependenciaResponseDto {
    private Integer id_dependencia;
    private String jefeDependencia;
    private String nombreDependencia;
    private String estado;
}
