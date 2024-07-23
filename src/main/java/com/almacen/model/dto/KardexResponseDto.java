package com.almacen.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KardexResponseDto {
    private ProductoResponseDto producto;
    private List<MovimientosResponseDto> movimientos;
}
