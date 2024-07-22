package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Dependencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_dependencia;

    @Column(nullable = false, length = 120)
    private String jefeDependencia;

    @Column(nullable = false, length = 50)
    private String nombreDependencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "dependencia")
    private Set<Unidad> unidades;

    @OneToMany(mappedBy = "dependencia")
    private Set<Solicitud> solicitudes;

    public enum Estado {
        HABILITADO, DESHABILITADO
    }
}
