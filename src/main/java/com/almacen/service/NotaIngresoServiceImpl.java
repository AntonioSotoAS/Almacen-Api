package com.almacen.service;

import com.almacen.model.dto.DetalleNotaIngresoRequestDto;
import com.almacen.model.dto.NotaIngresoRequestDto;
import com.almacen.model.dto.NotaIngresoResponseDto;
import com.almacen.model.entity.*;
import com.almacen.repository.DetalleNotaIngresoRepository;
import com.almacen.repository.MovimientosRepository;
import com.almacen.repository.NotaIngresoRepository;
import com.almacen.repository.ProductoRepository;
import com.almacen.repository.ProveedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaIngresoServiceImpl implements NotaIngresoService {

    @Autowired
    private NotaIngresoRepository notaIngresoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleNotaIngresoRepository detalleNotaIngresoRepository;

    @Autowired
    private MovimientosRepository movimientosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public NotaIngresoResponseDto createNotaIngreso(NotaIngresoRequestDto notaIngresoRequestDto) {
        NotaIngreso notaIngreso = new NotaIngreso();
        notaIngreso.setDcto_refe(generateUniqueDctoRefe());
        notaIngreso.setFecha(notaIngresoRequestDto.getFecha());
        notaIngreso.setTipoMov(NotaIngreso.TipoMov.valueOf(notaIngresoRequestDto.getTipoMov()));
        notaIngreso.setOcompra(generateUniqueOcompra());
        notaIngreso.setNguia(generateUniqueNguia());
        notaIngreso.setProveedor(proveedorRepository.findById(notaIngresoRequestDto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado")));

        notaIngreso = notaIngresoRepository.save(notaIngreso);

        for (DetalleNotaIngresoRequestDto detalleDto : notaIngresoRequestDto.getDetallesNotaIngreso()) {
            Producto producto = productoRepository.findById(detalleDto.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleNotaIngreso detalle = new DetalleNotaIngreso();
            detalle.setNotaIngreso(notaIngreso);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setPrecio(detalleDto.getPrecio());
            detalle.setIgv(detalleDto.getIgv());
            detalle.setTotal(detalleDto.getTotal());
            detalle.setProducto(producto);

            // Crear el movimiento de entrada
            Movimientos movimiento = new Movimientos();
            movimiento.setTipo(Movimientos.Tipo.ENTRADA);
            movimiento.setStockActual(detalle.getCantidad());
            movimiento.setDetalleNotaIngreso(detalle);

            // Guardar el movimiento
            movimiento = movimientosRepository.save(movimiento);

            // Asociar el movimiento con el detalle y guardar el detalle
            detalle.setMovimiento(movimiento);
            detalleNotaIngresoRepository.save(detalle);
        }

        return convertToDto(notaIngreso);
    }

    @Override
    public NotaIngresoResponseDto updateNotaIngreso(Integer id, NotaIngresoRequestDto notaIngresoRequestDto) {
        NotaIngreso notaIngreso = notaIngresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de ingreso no encontrada"));
        notaIngreso.setDcto_refe(generateUniqueDctoRefe());
        notaIngreso.setFecha(notaIngresoRequestDto.getFecha());
        notaIngreso.setTipoMov(NotaIngreso.TipoMov.valueOf(notaIngresoRequestDto.getTipoMov()));
        notaIngreso.setOcompra(generateUniqueOcompra());
        notaIngreso.setNguia(generateUniqueNguia());
        Proveedor proveedor = proveedorRepository.findById(notaIngresoRequestDto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        notaIngreso.setProveedor(proveedor);

        notaIngreso = notaIngresoRepository.save(notaIngreso);
        return convertToDto(notaIngreso);
    }

    private String generateUniqueOcompra() {
        return "OC" + System.currentTimeMillis();
    }

    private String generateUniqueNguia() {
        return "NG" + System.currentTimeMillis();
    }

    private String generateUniqueDctoRefe() {
        SecureRandom random = new SecureRandom();
        return "REF" + random.nextInt(1000000);
    }

    @Override
    public NotaIngresoResponseDto getNotaIngresoById(Integer id) {
        NotaIngreso notaIngreso = notaIngresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de ingreso no encontrada"));
        return convertToDto(notaIngreso);
    }

    @Override
    public List<NotaIngresoResponseDto> getAllNotasIngreso() {
        List<NotaIngreso> notasIngreso = notaIngresoRepository.findAll();
        return notasIngreso.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }




    @Override
    public void deleteNotaIngreso(Integer id) {
        notaIngresoRepository.deleteById(id);
    }

    private NotaIngresoResponseDto convertToDto(NotaIngreso notaIngreso) {
        NotaIngresoResponseDto dto = new NotaIngresoResponseDto();
        dto.setIdNotaIngreso(notaIngreso.getId_nota_ing());
        dto.setDctoRefe(notaIngreso.getDcto_refe());
        dto.setFecha(notaIngreso.getFecha());
        dto.setTipoMov(notaIngreso.getTipoMov().name());
        dto.setOcompra(notaIngreso.getOcompra());
        dto.setNguia(notaIngreso.getNguia());
        dto.setIdProveedor(notaIngreso.getProveedor().getId_proveedor());
        dto.setProveedorNombre(notaIngreso.getProveedor().getNombreProv());
        return dto;
    }


}
