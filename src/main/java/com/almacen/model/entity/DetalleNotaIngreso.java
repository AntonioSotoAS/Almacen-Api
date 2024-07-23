package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class DetalleNotaIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_nta_ingreso;

    @ManyToOne
    @JoinColumn(name = "id_nota_ing", nullable = false)
    private NotaIngreso notaIngreso;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, precision = 8, scale = 3)
    private BigDecimal precio;

    @Column(nullable = false, precision = 8, scale = 3)
    private BigDecimal igv;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "id_movimiento", nullable = false)
    private Movimientos movimiento;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

}
