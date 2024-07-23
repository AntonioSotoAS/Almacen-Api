package com.almacen.service;

import com.almacen.model.dto.KardexResponseDto;

public interface KardexService {
    KardexResponseDto getKardex(Integer idProducto);
}
