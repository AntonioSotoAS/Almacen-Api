package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SolicitudResponseDto {
    private Integer id;
    private String dependencia;
    private String motivo;
    private BigDecimal importe;
    private String estado;
    private Date fecha;
    private List<DetalleSolicitudResponseDto> detallesSolicitud;
}
