package com.almacen.service;

import com.almacen.model.dto.DetalleSolicitudRequestDto;
import com.almacen.model.dto.DetalleSolicitudResponseDto;

import java.util.List;

public interface DetalleSolicitudService {
    DetalleSolicitudResponseDto createDetalleSolicitud(DetalleSolicitudRequestDto detalleSolicitudRequestDto);
    DetalleSolicitudResponseDto updateDetalleSolicitud(Integer id, DetalleSolicitudRequestDto detalleSolicitudRequestDto);
    DetalleSolicitudResponseDto getDetalleSolicitudById(Integer id);
    List<DetalleSolicitudResponseDto> getAllDetalleSolicitudes();
    void deleteDetalleSolicitud(Integer id);
}
