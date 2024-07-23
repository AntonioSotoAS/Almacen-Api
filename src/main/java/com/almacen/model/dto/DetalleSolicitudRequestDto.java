package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DetalleSolicitudRequestDto {
    private Integer idProducto;
    private Date fecha;
    private Integer cantidad;
    private BigDecimal total;
        private Integer idSolicitud;

}
