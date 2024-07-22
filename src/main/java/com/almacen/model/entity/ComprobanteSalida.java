package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class ComprobanteSalida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_comprobante_salida;

    @Column(nullable = false, length = 20)
    private String n_pecosa;

    @Column(nullable = false)
    private Date fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "comprobanteSalida")
    private Set<DetalleComprobanteSalida> detallesComprobanteSalida;

    public enum Estado {
        APROBADO, ANULADO
    }
}