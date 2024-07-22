package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 18)
    private String username;

    @Column(nullable = false, length = 255)  // Asegúrate de que la longitud aquí sea 255
    private String clave;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    public enum Rol {
        ADMINISTRADOR, JEFE_DE_UNIDAD, JEFE_DE_DEPENDENCIA, JEFE_DE_ALMACEN
    }

    public enum Estado {
        HABILITADO, DESHABILITADO
    }
}
