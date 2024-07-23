package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ComprobanteSalidaRequestDto {
    private Date fecha;
    private String estado;
    private List<DetalleComprobanteSalidaRequestDto> detallesComprobanteSalida;
}