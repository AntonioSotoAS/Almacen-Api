package com.almacen.service;

import com.almacen.model.dto.SolicitudRequestDto;
import com.almacen.model.dto.SolicitudResponseDto;
import com.almacen.model.entity.DetalleSolicitud;
import com.almacen.model.entity.Solicitud;
import com.almacen.repository.DependenciaRepository;
import com.almacen.repository.DetalleSolicitudRepository;
import com.almacen.repository.ProductoRepository;
import com.almacen.repository.SolicitudRepository;
import com.almacen.service.SolicitudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private DependenciaRepository dependenciaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DetalleSolicitudRepository detalleSolicitudRepository;

    @Override
    public SolicitudResponseDto createSolicitud(SolicitudRequestDto solicitudRequestDto) {
        Solicitud solicitud = new Solicitud();
        solicitud.setDependencia(dependenciaRepository.findById(solicitudRequestDto.getIdDependencia())
                .orElseThrow(() -> new RuntimeException("Dependencia no encontrada")));
        solicitud.setMotivo(solicitudRequestDto.getMotivo());
        solicitud.setImporte(solicitudRequestDto.getImporte().doubleValue());
        solicitud.setEstado(solicitudRequestDto.getEstado());
        solicitud.setFecha(solicitudRequestDto.getFecha());

        List<DetalleSolicitud> detalles = solicitudRequestDto.getDetallesSolicitud().stream()
                .map(detalleDto -> {
                    DetalleSolicitud detalle = new DetalleSolicitud();
                    detalle.setSolicitud(solicitud);
                    detalle.setProducto(productoRepository.findById(detalleDto.getIdProducto())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
                    detalle.setCantidad(detalleDto.getCantidad());
                    detalle.setTotal(detalleDto.getTotal());
                    return detalle;
                }).collect(Collectors.toList());
        solicitud.setDetallesSolicitud(detalles);

        Solicitud savedSolicitud = solicitudRepository.save(solicitud);
        return modelMapper.map(savedSolicitud, SolicitudResponseDto.class);
    }

    @Override
    public SolicitudResponseDto updateSolicitud(Integer id, SolicitudRequestDto solicitudRequestDto) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitud.setDependencia(dependenciaRepository.findById(solicitudRequestDto.getIdDependencia())
                .orElseThrow(() -> new RuntimeException("Dependencia no encontrada")));
        solicitud.setMotivo(solicitudRequestDto.getMotivo());
        solicitud.setImporte(solicitudRequestDto.getImporte().doubleValue());
        solicitud.setEstado(solicitudRequestDto.getEstado());
        solicitud.setFecha(solicitudRequestDto.getFecha());

        Solicitud updatedSolicitud = solicitudRepository.save(solicitud);
        return mapToResponseDto(updatedSolicitud);
    }

    @Override
    public SolicitudResponseDto getSolicitudById(Integer id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        return mapToResponseDto(solicitud);
    }

    @Override
    public List<SolicitudResponseDto> getAllSolicitudes() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        return solicitudes.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSolicitud(Integer id) {
        solicitudRepository.deleteById(id);
    }

    private SolicitudResponseDto mapToResponseDto(Solicitud solicitud) {
        return modelMapper.map(solicitud, SolicitudResponseDto.class);
    }
}
