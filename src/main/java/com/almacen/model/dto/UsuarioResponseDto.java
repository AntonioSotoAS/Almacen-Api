package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDto {
    private Integer id_usuario;
    private String username;
    private String email;
    private String rol;
    private String estado;
}
