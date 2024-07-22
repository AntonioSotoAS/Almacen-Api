package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_unidad;

    @Column(nullable = false, length = 120)
    private String jefeUnidad;

    @Column(nullable = false, length = 50)
    private String nombreUnidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_dependencia", nullable = false)
    private Dependencia dependencia;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    public enum Estado {
        HABILITADO, DESHABILITADO
    }
}
