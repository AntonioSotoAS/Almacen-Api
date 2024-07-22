package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_proveedor;

    @Column(nullable = false, length = 50)
    private String nombreProv;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "proveedor")
    private Set<Unidad> unidades;

    @OneToMany(mappedBy = "proveedor")
    private Set<NotaIngreso> notasIngreso;

    public enum Estado {
        HABILITADO, DESHABILITADO
    }
}