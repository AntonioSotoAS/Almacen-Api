package com.almacen.service;

import com.almacen.model.dto.MovimientosRequestDto;
import com.almacen.model.dto.MovimientosResponseDto;
import com.almacen.model.entity.DetalleComprobanteSalida;
import com.almacen.model.entity.Movimientos;
import com.almacen.repository.DetalleComprobanteSalidaRepository;
import com.almacen.repository.MovimientosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientosServiceImpl implements MovimientosService {

    @Autowired
    private MovimientosRepository movimientosRepository;

    @Autowired
    private DetalleComprobanteSalidaRepository detalleComprobanteSalidaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public MovimientosResponseDto createMovimiento(MovimientosRequestDto movimientosRequestDto) {
        Movimientos movimiento = new Movimientos();
        movimiento.setTipo(Movimientos.Tipo.valueOf(movimientosRequestDto.getTipo()));
        movimiento.setStockActual(movimientosRequestDto.getStockActual());

        DetalleComprobanteSalida detalleComprobanteSalida = detalleComprobanteSalidaRepository.findById(movimientosRequestDto.getDetalleComprobanteSalidaId())
                .orElseThrow(() -> new RuntimeException("Detalle Comprobante Salida no encontrado"));
        movimiento.setDetalleComprobanteSalida(detalleComprobanteSalida);

        movimiento = movimientosRepository.save(movimiento);

        return modelMapper.map(movimiento, MovimientosResponseDto.class);
    }

    @Override
    public MovimientosResponseDto updateMovimiento(Integer id, MovimientosRequestDto movimientosRequestDto) {
        Movimientos movimiento = movimientosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        movimiento.setTipo(Movimientos.Tipo.valueOf(movimientosRequestDto.getTipo()));
        movimiento.setStockActual(movimientosRequestDto.getStockActual());

        DetalleComprobanteSalida detalleComprobanteSalida = detalleComprobanteSalidaRepository.findById(movimientosRequestDto.getDetalleComprobanteSalidaId())
                .orElseThrow(() -> new RuntimeException("Detalle Comprobante Salida no encontrado"));
        movimiento.setDetalleComprobanteSalida(detalleComprobanteSalida);

        movimiento = movimientosRepository.save(movimiento);

        return modelMapper.map(movimiento, MovimientosResponseDto.class);
    }

    @Override
    public MovimientosResponseDto getMovimientoById(Integer id) {
        Movimientos movimiento = movimientosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        return modelMapper.map(movimiento, MovimientosResponseDto.class);
    }

    @Override
    public List<MovimientosResponseDto> getAllMovimientos() {
        List<Movimientos> movimientos = movimientosRepository.findAll();
        return movimientos.stream()
                .map(movimiento -> modelMapper.map(movimiento, MovimientosResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMovimiento(Integer id) {
        movimientosRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getStockActualByProductoId(Integer productoId) {
        Integer stockEntradas = movimientosRepository.sumEntradasByProductoId(productoId);
        Integer stockSalidas = movimientosRepository.sumSalidasByProductoId(productoId);

        if (stockEntradas == null) {
            stockEntradas = 0;
        }
        if (stockSalidas == null) {
            stockSalidas = 0;
        }

        return stockEntradas - stockSalidas;
    }
}
