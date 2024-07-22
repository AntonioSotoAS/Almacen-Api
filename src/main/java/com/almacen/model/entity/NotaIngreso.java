package com.almacen.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class NotaIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_nota_ing;

    @Column(nullable = false, length = 25)
    private String dcto_refe;

    @Column(nullable = false)
    private Date fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMov tipoMov;

    @Column(nullable = false, length = 55)
    private String ocompra;

    @Column(nullable = false, length = 20)
    private String nguia;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    public enum TipoMov {
        DONACION, COMPRA
    }
}
