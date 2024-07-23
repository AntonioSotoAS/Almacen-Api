package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movimientos")
public class Movimientos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_detalle_comp_salida", nullable = true)
    private DetalleComprobanteSalida detalleComprobanteSalida;

    @ManyToOne
    @JoinColumn(name = "id_detalle_nta_ingreso", nullable = true)
    private DetalleNotaIngreso detalleNotaIngreso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false)
    private Integer stockActual;

    public enum Tipo {
        ENTRADA, SALIDA
    }
}
