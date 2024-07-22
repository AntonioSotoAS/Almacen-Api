package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movimientos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_movimiento;

    @ManyToOne
    @JoinColumn(name = "id_detalle_comp_salida", nullable = false)
    private DetalleComprobanteSalida detalleComprobanteSalida;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false)
    private Integer stock_actual;

    public enum Tipo {
        ENTRADA, SALIDA
    }
}