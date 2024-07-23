package com.almacen.service.impl;

import com.almacen.model.dto.DetalleComprobanteSalidaRequestDto;
import com.almacen.model.dto.DetalleComprobanteSalidaResponseDto;
import com.almacen.model.entity.DetalleComprobanteSalida;
import com.almacen.repository.DetalleComprobanteSalidaRepository;
import com.almacen.repository.ProductoRepository;
import com.almacen.repository.ComprobanteSalidaRepository;
import com.almacen.service.DetalleComprobanteSalidaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleComprobanteSalidaServiceImpl implements DetalleComprobanteSalidaService {

    @Autowired
    private DetalleComprobanteSalidaRepository detalleComprobanteSalidaRepository;

    @Autowired
    private ComprobanteSalidaRepository comprobanteSalidaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DetalleComprobanteSalidaResponseDto createDetalleComprobanteSalida(DetalleComprobanteSalidaRequestDto detalleComprobanteSalidaRequestDto) {
        DetalleComprobanteSalida detalleComprobanteSalida = new DetalleComprobanteSalida();
        detalleComprobanteSalida.setCantidad(detalleComprobanteSalidaRequestDto.getCantidad());
        detalleComprobanteSalida.setProducto(productoRepository.findById(detalleComprobanteSalidaRequestDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        detalleComprobanteSalida.setComprobanteSalida(comprobanteSalidaRepository.findById(detalleComprobanteSalidaRequestDto.getIdComprobanteSalida())
                .orElseThrow(() -> new RuntimeException("Comprobante de salida no encontrado")));

        DetalleComprobanteSalida savedDetalleComprobanteSalida = detalleComprobanteSalidaRepository.save(detalleComprobanteSalida);
        return modelMapper.map(savedDetalleComprobanteSalida, DetalleComprobanteSalidaResponseDto.class);
    }

    @Override
    public DetalleComprobanteSalidaResponseDto updateDetalleComprobanteSalida(Integer id, DetalleComprobanteSalidaRequestDto detalleComprobanteSalidaRequestDto) {
        DetalleComprobanteSalida detalleComprobanteSalida = detalleComprobanteSalidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de comprobante de salida no encontrado"));
        detalleComprobanteSalida.setCantidad(detalleComprobanteSalidaRequestDto.getCantidad());
        detalleComprobanteSalida.setProducto(productoRepository.findById(detalleComprobanteSalidaRequestDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        detalleComprobanteSalida.setComprobanteSalida(comprobanteSalidaRepository.findById(detalleComprobanteSalidaRequestDto.getIdComprobanteSalida())
                .orElseThrow(() -> new RuntimeException("Comprobante de salida no encontrado")));

        DetalleComprobanteSalida updatedDetalleComprobanteSalida = detalleComprobanteSalidaRepository.save(detalleComprobanteSalida);
        return modelMapper.map(updatedDetalleComprobanteSalida, DetalleComprobanteSalidaResponseDto.class);
    }

    @Override
    public DetalleComprobanteSalidaResponseDto getDetalleComprobanteSalidaById(Integer id) {
        DetalleComprobanteSalida detalleComprobanteSalida = detalleComprobanteSalidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de comprobante de salida no encontrado"));
        return modelMapper.map(detalleComprobanteSalida, DetalleComprobanteSalidaResponseDto.class);
    }

    @Override
    public List<DetalleComprobanteSalidaResponseDto> getAllDetallesComprobanteSalida() {
        List<DetalleComprobanteSalida> detallesComprobanteSalida = detalleComprobanteSalidaRepository.findAll();
        return detallesComprobanteSalida.stream()
                .map(detalleComprobanteSalida -> modelMapper.map(detalleComprobanteSalida, DetalleComprobanteSalidaResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDetalleComprobanteSalida(Integer id) {
        detalleComprobanteSalidaRepository.deleteById(id);
    }
}
