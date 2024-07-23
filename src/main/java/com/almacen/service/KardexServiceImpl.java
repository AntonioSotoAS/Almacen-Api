package com.almacen.service;

import com.almacen.config.DateUtils;
import com.almacen.model.dto.KardexResponseDto;
import com.almacen.model.dto.MovimientosResponseDto;
import com.almacen.model.dto.ProductoResponseDto;
import com.almacen.model.entity.Movimientos;
import com.almacen.model.entity.Producto;
import com.almacen.repository.MovimientosRepository;
import com.almacen.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KardexServiceImpl implements KardexService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MovimientosRepository movimientosRepository;

    @Override
    public KardexResponseDto getKardex(Integer idProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        List<Movimientos> movimientos = movimientosRepository.findMovimientosByProductoId(idProducto);


        ProductoResponseDto productoDto = new ProductoResponseDto(
                producto.getId_producto().toString(),
                producto.getCod(),
                producto.getDescripcion(),
                producto.getCategoria().getNombre_categoria(),
                producto.getPrecio(),
                producto.getEstado(),
                producto.getUnidadMedida().getTipo_unidad(),
                producto.getId_producto().toString()
        );

        List<MovimientosResponseDto> movimientoDtos = movimientos.stream().map(m -> {
            MovimientosResponseDto dto = new MovimientosResponseDto();
            if (m.getDetalleNotaIngreso() != null) {
                dto.setIdMovimiento(m.getIdMovimiento());
                dto.setDetalleNotaIngresoId(m.getDetalleNotaIngreso().getId_detalle_nta_ingreso());
                dto.setFecha(DateUtils.convertToLocalDate(m.getDetalleNotaIngreso().getNotaIngreso().getFecha()));
                dto.setDescripcion("ENTRADA " + m.getDetalleNotaIngreso().getNotaIngreso().getDcto_refe());
                dto.setCostoUnitario(m.getDetalleNotaIngreso().getPrecio());
                dto.setTipo(m.getTipo().toString());
                dto.setStockActual(m.getStockActual());
            } else if (m.getDetalleComprobanteSalida() != null) {
                dto.setIdMovimiento(m.getIdMovimiento());
                dto.setDetalleComprobanteSalidaId(m.getDetalleComprobanteSalida().getIdDetalleCompSalida());
                dto.setFecha(DateUtils.convertToLocalDate(m.getDetalleComprobanteSalida().getComprobanteSalida().getFecha()));
                dto.setDescripcion("SALIDA " + m.getDetalleComprobanteSalida().getComprobanteSalida().getNPecosa());
                dto.setTipo(m.getTipo().toString());
                dto.setCostoUnitario(m.getDetalleComprobanteSalida().getProducto().getPrecio());
                dto.setStockActual(m.getStockActual());
            }
            dto.setEntrada(m.getTipo() == Movimientos.Tipo.ENTRADA ? m.getStockActual() : 0);
            dto.setSalida(m.getTipo() == Movimientos.Tipo.SALIDA ? m.getStockActual() : 0);
            dto.setStock(m.getStockActual());
            return dto;
        }).collect(Collectors.toList());

        KardexResponseDto kardex = new KardexResponseDto();
        kardex.setProducto(productoDto);
        kardex.setMovimientos(movimientoDtos);

        return kardex;
    }
}
