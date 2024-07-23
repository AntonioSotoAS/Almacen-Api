package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comprobante_salida")
public class ComprobanteSalida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprobanteSalida;

    @Getter @Setter
    private Date fecha;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Getter @Setter
    @Column(nullable = true)
    private String nPecosa;

    @OneToMany(mappedBy = "comprobanteSalida", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleComprobanteSalida> detallesComprobanteSalida;

    public enum Estado {
        APROBADO,
        PENDIENTE,
        RECHAZADO
    }

}
