package com.almacen.repository;

import com.almacen.model.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Integer> {

    @Query("SELECT COALESCE(SUM(m.stockActual), 0) FROM Movimientos m " +
            "WHERE m.detalleNotaIngreso.producto.id_producto = :productoId AND m.tipo = 'ENTRADA'")
    Integer sumEntradasByProductoId(Integer productoId);

    @Query("SELECT COALESCE(SUM(m.stockActual), 0) FROM Movimientos m " +
            "WHERE m.detalleComprobanteSalida.producto.id_producto = :productoId AND m.tipo = 'SALIDA'")
    Integer sumSalidasByProductoId(Integer productoId);
}
