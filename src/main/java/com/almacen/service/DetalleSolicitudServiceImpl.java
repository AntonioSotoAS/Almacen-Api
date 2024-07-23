package com.almacen.service;

import com.almacen.model.dto.DetalleSolicitudRequestDto;
import com.almacen.model.dto.DetalleSolicitudResponseDto;
import com.almacen.model.entity.DetalleSolicitud;
import com.almacen.repository.DetalleSolicitudRepository;
import com.almacen.repository.ProductoRepository;
import com.almacen.repository.SolicitudRepository;
import com.almacen.service.DetalleSolicitudService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleSolicitudServiceImpl implements DetalleSolicitudService {

    @Autowired
    private DetalleSolicitudRepository detalleSolicitudRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureModelMapper() {
        modelMapper.typeMap(DetalleSolicitud.class, DetalleSolicitudResponseDto.class).addMapping(
                src -> src.getProducto().getMarca().getNombre_marca(), DetalleSolicitudResponseDto::setProductoNombre
        );
    }

    @Override
    public DetalleSolicitudResponseDto createDetalleSolicitud(DetalleSolicitudRequestDto detalleSolicitudRequestDto) {
        DetalleSolicitud detalleSolicitud = new DetalleSolicitud();
        detalleSolicitud.setSolicitud(solicitudRepository.findById(detalleSolicitudRequestDto.getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada")));
        detalleSolicitud.setProducto(productoRepository.findById(detalleSolicitudRequestDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        detalleSolicitud.setCantidad(detalleSolicitudRequestDto.getCantidad());
        detalleSolicitud.setTotal(detalleSolicitudRequestDto.getTotal());

        DetalleSolicitud savedDetalleSolicitud = detalleSolicitudRepository.save(detalleSolicitud);
        return modelMapper.map(savedDetalleSolicitud, DetalleSolicitudResponseDto.class);
    }

    @Override
    public DetalleSolicitudResponseDto updateDetalleSolicitud(Integer id, DetalleSolicitudRequestDto detalleSolicitudRequestDto) {
        DetalleSolicitud detalleSolicitud = detalleSolicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleSolicitud no encontrado"));
        detalleSolicitud.setSolicitud(solicitudRepository.findById(detalleSolicitudRequestDto.getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada")));
        detalleSolicitud.setProducto(productoRepository.findById(detalleSolicitudRequestDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        detalleSolicitud.setCantidad(detalleSolicitudRequestDto.getCantidad());
        detalleSolicitud.setTotal(detalleSolicitudRequestDto.getTotal());

        DetalleSolicitud updatedDetalleSolicitud = detalleSolicitudRepository.save(detalleSolicitud);
        return modelMapper.map(updatedDetalleSolicitud, DetalleSolicitudResponseDto.class);
    }

    @Override
    public DetalleSolicitudResponseDto getDetalleSolicitudById(Integer id) {
        DetalleSolicitud detalleSolicitud = detalleSolicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleSolicitud no encontrado"));
        return modelMapper.map(detalleSolicitud, DetalleSolicitudResponseDto.class);
    }

    @Override
    public List<DetalleSolicitudResponseDto> getAllDetalleSolicitudes() {
        List<DetalleSolicitud> detalleSolicitudes = detalleSolicitudRepository.findAll();
        return detalleSolicitudes.stream()
                .map(detalleSolicitud -> modelMapper.map(detalleSolicitud, DetalleSolicitudResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDetalleSolicitud(Integer id) {
        detalleSolicitudRepository.deleteById(id);
    }
}
