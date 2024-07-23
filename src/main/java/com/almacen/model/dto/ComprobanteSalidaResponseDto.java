package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ComprobanteSalidaResponseDto {
    private Integer idComprobanteSalida;
    private String nPecosa;
    private Date fecha;
    private String estado;
    private List<DetalleComprobanteSalidaResponseDto> detallesComprobanteSalida;
}