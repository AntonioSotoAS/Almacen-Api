package com.almacen.controller;

import com.almacen.model.dto.SolicitudRequestDto;
import com.almacen.model.dto.SolicitudResponseDto;
import com.almacen.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping
    public ResponseEntity<SolicitudResponseDto> createSolicitud(@RequestBody SolicitudRequestDto solicitudRequestDto) {
        SolicitudResponseDto solicitudResponseDto = solicitudService.createSolicitud(solicitudRequestDto);
        return ResponseEntity.ok(solicitudResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudResponseDto> updateSolicitud(@PathVariable Integer id, @RequestBody SolicitudRequestDto solicitudRequestDto) {
        SolicitudResponseDto solicitudResponseDto = solicitudService.updateSolicitud(id, solicitudRequestDto);
        return ResponseEntity.ok(solicitudResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudResponseDto> getSolicitudById(@PathVariable Integer id) {
        SolicitudResponseDto solicitudResponseDto = solicitudService.getSolicitudById(id);
        return ResponseEntity.ok(solicitudResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<SolicitudResponseDto>> getAllSolicitudes() {
        List<SolicitudResponseDto> solicitudes = solicitudService.getAllSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Integer id) {
        solicitudService.deleteSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
