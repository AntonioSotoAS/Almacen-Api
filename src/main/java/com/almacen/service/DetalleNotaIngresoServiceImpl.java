package com.almacen.service;

import com.almacen.model.dto.DetalleNotaIngresoRequestDto;
import com.almacen.model.dto.DetalleNotaIngresoResponseDto;
import com.almacen.model.entity.*;
import com.almacen.repository.*;
import com.almacen.service.DetalleNotaIngresoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleNotaIngresoServiceImpl implements DetalleNotaIngresoService {

    @Autowired
    private DetalleNotaIngresoRepository detalleNotaIngresoRepository;

    @Autowired
    private NotaIngresoRepository notaIngresoRepository;

    @Autowired
    private MovimientosRepository movimientosRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DetalleNotaIngresoResponseDto createDetalleNotaIngreso(DetalleNotaIngresoRequestDto detalleNotaIngresoRequestDto) {
        DetalleNotaIngreso detalleNotaIngreso = new DetalleNotaIngreso();
        detalleNotaIngreso.setCantidad(detalleNotaIngresoRequestDto.getCantidad());
        detalleNotaIngreso.setPrecio(detalleNotaIngresoRequestDto.getPrecio());
        detalleNotaIngreso.setIgv(detalleNotaIngresoRequestDto.getIgv());
        detalleNotaIngreso.setTotal(detalleNotaIngresoRequestDto.getTotal());

        NotaIngreso notaIngreso = notaIngresoRepository.findById(detalleNotaIngresoRequestDto.getIdNotaIngreso())
                .orElseThrow(() -> new RuntimeException("Nota de ingreso no encontrada"));
        detalleNotaIngreso.setNotaIngreso(notaIngreso);

        Producto producto = productoRepository.findById(detalleNotaIngresoRequestDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        detalleNotaIngreso.setProducto(producto);

        detalleNotaIngreso = detalleNotaIngresoRepository.save(detalleNotaIngreso);

        // Crear el movimiento de entrada
        Movimientos movimiento = new Movimientos();
        movimiento.setDetalleNotaIngreso(detalleNotaIngreso);
        movimiento.setTipo(Movimientos.Tipo.ENTRADA);
        movimiento.setStockActual(detalleNotaIngreso.getCantidad());

        movimientosRepository.save(movimiento);

        return convertToDto(detalleNotaIngreso);
    }

    @Override
    public DetalleNotaIngresoResponseDto updateDetalleNotaIngreso(Integer id, DetalleNotaIngresoRequestDto detalleNotaIngresoRequestDto) {
        DetalleNotaIngreso detalleNotaIngreso = detalleNotaIngresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de nota de ingreso no encontrado"));
        detalleNotaIngreso.setCantidad(detalleNotaIngresoRequestDto.getCantidad());
        detalleNotaIngreso.setPrecio(detalleNotaIngresoRequestDto.getPrecio());
        detalleNotaIngreso.setIgv(detalleNotaIngresoRequestDto.getIgv());
        detalleNotaIngreso.setTotal(detalleNotaIngresoRequestDto.getTotal());

        NotaIngreso notaIngreso = notaIngresoRepository.findById(detalleNotaIngresoRequestDto.getIdNotaIngreso())
                .orElseThrow(() -> new RuntimeException("Nota de ingreso no encontrada"));
        detalleNotaIngreso.setNotaIngreso(notaIngreso);

        Producto producto = productoRepository.findById(detalleNotaIngresoRequestDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        detalleNotaIngreso.setProducto(producto);

        detalleNotaIngreso = detalleNotaIngresoRepository.save(detalleNotaIngreso);

        return convertToDto(detalleNotaIngreso);
    }

    @Override
    public DetalleNotaIngresoResponseDto getDetalleNotaIngresoById(Integer id) {
        DetalleNotaIngreso detalleNotaIngreso = detalleNotaIngresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de nota de ingreso no encontrado"));
        return convertToDto(detalleNotaIngreso);
    }

    @Override
    public List<DetalleNotaIngresoResponseDto> getAllDetallesNotaIngreso() {
        List<DetalleNotaIngreso> detallesNotaIngreso = detalleNotaIngresoRepository.findAll();
        return detallesNotaIngreso.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDetalleNotaIngreso(Integer id) {
        detalleNotaIngresoRepository.deleteById(id);
    }

    private DetalleNotaIngresoResponseDto convertToDto(DetalleNotaIngreso detalleNotaIngreso) {
        DetalleNotaIngresoResponseDto dto = modelMapper.map(detalleNotaIngreso, DetalleNotaIngresoResponseDto.class);
        dto.setIdNotaIngreso(detalleNotaIngreso.getNotaIngreso().getId_nota_ing());
        dto.setIdProducto(detalleNotaIngreso.getProducto().getId_producto());
        dto.setProductoNombre(detalleNotaIngreso.getProducto().getMarca().toString());
        return dto;
    }
}
