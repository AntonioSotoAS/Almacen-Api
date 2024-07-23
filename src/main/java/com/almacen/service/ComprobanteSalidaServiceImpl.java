package com.almacen.service;

import com.almacen.model.dto.ComprobanteSalidaRequestDto;
import com.almacen.model.dto.ComprobanteSalidaResponseDto;
import com.almacen.model.dto.DetalleComprobanteSalidaRequestDto;
import com.almacen.model.entity.ComprobanteSalida;
import com.almacen.model.entity.DetalleComprobanteSalida;
import com.almacen.model.entity.Movimientos;
import com.almacen.repository.ComprobanteSalidaRepository;
import com.almacen.repository.DetalleComprobanteSalidaRepository;
import com.almacen.repository.MovimientosRepository;
import com.almacen.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComprobanteSalidaServiceImpl implements ComprobanteSalidaService {

    @Autowired
    private ComprobanteSalidaRepository comprobanteSalidaRepository;

    @Autowired
    private DetalleComprobanteSalidaRepository detalleComprobanteSalidaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MovimientosRepository movimientosRepository;

    @Override
    @Transactional
    public ComprobanteSalidaResponseDto createComprobanteSalida(ComprobanteSalidaRequestDto comprobanteSalidaRequestDto) {
        ComprobanteSalida comprobanteSalida = new ComprobanteSalida();
        comprobanteSalida.setFecha(comprobanteSalidaRequestDto.getFecha());
        comprobanteSalida.setEstado(ComprobanteSalida.Estado.valueOf(comprobanteSalidaRequestDto.getEstado()));

        // Asignar un valor temporal a nPecosa
        comprobanteSalida.setNPecosa("TEMP");

        // Primero guardamos la entidad para generar el ID
        comprobanteSalida = comprobanteSalidaRepository.save(comprobanteSalida);

        // Luego actualizamos el campo nPecosa con el valor correcto
        comprobanteSalida.setNPecosa("P-" + comprobanteSalida.getIdComprobanteSalida());
        comprobanteSalida = comprobanteSalidaRepository.save(comprobanteSalida);

        for (DetalleComprobanteSalidaRequestDto detalleDto : comprobanteSalidaRequestDto.getDetallesComprobanteSalida()) {
            DetalleComprobanteSalida detalle = new DetalleComprobanteSalida();
            detalle.setComprobanteSalida(comprobanteSalida);
            detalle.setProducto(productoRepository.findById(detalleDto.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
            detalle.setCantidad(detalleDto.getCantidad());
            detalleComprobanteSalidaRepository.save(detalle);

            // Generar el movimiento de salida
            Movimientos movimiento = new Movimientos();
            movimiento.setDetalleComprobanteSalida(detalle);
            movimiento.setTipo(Movimientos.Tipo.SALIDA);
            movimiento.setStockActual(detalleDto.getCantidad()); // Ajusta este valor según la lógica de tu negocio
            movimientosRepository.save(movimiento);

        }

        // Obtener los detalles nuevamente después de guardarlos
        comprobanteSalida.setDetallesComprobanteSalida(
                detalleComprobanteSalidaRepository.findByComprobanteSalidaIdComprobanteSalida(comprobanteSalida.getIdComprobanteSalida())
        );

        return modelMapper.map(comprobanteSalida, ComprobanteSalidaResponseDto.class);
    }


    @Override
    public ComprobanteSalidaResponseDto updateComprobanteSalida(Integer id, ComprobanteSalidaRequestDto comprobanteSalidaRequestDto) {
        ComprobanteSalida comprobanteSalida = comprobanteSalidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comprobante de salida no encontrado"));
        comprobanteSalida.setFecha(comprobanteSalidaRequestDto.getFecha());
        comprobanteSalida.setEstado(ComprobanteSalida.Estado.valueOf(comprobanteSalidaRequestDto.getEstado()));

        comprobanteSalida = comprobanteSalidaRepository.save(comprobanteSalida);
        return modelMapper.map(comprobanteSalida, ComprobanteSalidaResponseDto.class);
    }

    @Override
    public ComprobanteSalidaResponseDto getComprobanteSalidaById(Integer id) {
        ComprobanteSalida comprobanteSalida = comprobanteSalidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comprobante de salida no encontrado"));
        return modelMapper.map(comprobanteSalida, ComprobanteSalidaResponseDto.class);
    }

    @Override
    public List<ComprobanteSalidaResponseDto> getAllComprobantesSalida() {
        List<ComprobanteSalida> comprobantesSalida = comprobanteSalidaRepository.findAll();
        return comprobantesSalida.stream()
                .map(comprobanteSalida -> modelMapper.map(comprobanteSalida, ComprobanteSalidaResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComprobanteSalida(Integer id) {
        comprobanteSalidaRepository.deleteById(id);
    }
}
