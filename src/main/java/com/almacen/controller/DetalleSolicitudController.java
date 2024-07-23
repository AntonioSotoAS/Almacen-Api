package com.almacen.controller;

import com.almacen.model.dto.DetalleSolicitudRequestDto;
import com.almacen.model.dto.DetalleSolicitudResponseDto;
import com.almacen.service.DetalleSolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles-solicitud")
public class DetalleSolicitudController {

    @Autowired
    private DetalleSolicitudService detalleSolicitudService;

    @PostMapping
    public ResponseEntity<DetalleSolicitudResponseDto> createDetalleSolicitud(@RequestBody DetalleSolicitudRequestDto detalleSolicitudRequestDto) {
        DetalleSolicitudResponseDto createdDetalleSolicitud = detalleSolicitudService.createDetalleSolicitud(detalleSolicitudRequestDto);
        return ResponseEntity.ok(createdDetalleSolicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleSolicitudResponseDto> updateDetalleSolicitud(@PathVariable Integer id, @RequestBody DetalleSolicitudRequestDto detalleSolicitudRequestDto) {
        DetalleSolicitudResponseDto updatedDetalleSolicitud = detalleSolicitudService.updateDetalleSolicitud(id, detalleSolicitudRequestDto);
        return ResponseEntity.ok(updatedDetalleSolicitud);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleSolicitudResponseDto> getDetalleSolicitudById(@PathVariable Integer id) {
        DetalleSolicitudResponseDto detalleSolicitud = detalleSolicitudService.getDetalleSolicitudById(id);
        return ResponseEntity.ok(detalleSolicitud);
    }

    @GetMapping
    public ResponseEntity<List<DetalleSolicitudResponseDto>> getAllDetalleSolicitudes() {
        List<DetalleSolicitudResponseDto> detalleSolicitudes = detalleSolicitudService.getAllDetalleSolicitudes();
        return ResponseEntity.ok(detalleSolicitudes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleSolicitud(@PathVariable Integer id) {
        detalleSolicitudService.deleteDetalleSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
