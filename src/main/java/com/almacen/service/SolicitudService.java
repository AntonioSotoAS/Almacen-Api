package com.almacen.service;

import com.almacen.model.dto.SolicitudRequestDto;
import com.almacen.model.dto.SolicitudResponseDto;

import java.util.List;

public interface SolicitudService {
    SolicitudResponseDto createSolicitud(SolicitudRequestDto solicitudRequestDto);
    SolicitudResponseDto updateSolicitud(Integer id, SolicitudRequestDto solicitudRequestDto);
    SolicitudResponseDto getSolicitudById(Integer id);
    List<SolicitudResponseDto> getAllSolicitudes();
    void deleteSolicitud(Integer id);
}
