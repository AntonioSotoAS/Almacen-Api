package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateOrUpdateDto {
    private Integer id;
    private String username;
    private String email;
    private String clave;
    private String rol;
    private String estado;
}
