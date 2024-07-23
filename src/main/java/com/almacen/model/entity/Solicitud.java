package com.almacen.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_solicitud;

    @ManyToOne
    @JoinColumn(name = "id_dependencia", nullable = false)
    private Dependencia dependencia;

    @Column(nullable = false, length = 45)
    private String motivo;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal importe;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(nullable = false)
    private Date fecha;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleSolicitud> detallesSolicitud;

    public enum Estado {
        PENDIENTE, APROBADA, RECHAZADA
    }
}
