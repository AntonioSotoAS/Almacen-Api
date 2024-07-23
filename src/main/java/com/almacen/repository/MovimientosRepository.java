package com.almacen.repository;

import com.almacen.model.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Integer> {

    @Query("SELECT COALESCE(SUM(m.stockActual), 0) FROM Movimientos m " +
            "WHERE m.detalleNotaIngreso.producto.id_producto = :productoId AND m.tipo = 'ENTRADA'")
    Integer sumEntradasByProductoId(Integer productoId);

    @Query("SELECT COALESCE(SUM(m.stockActual), 0) FROM Movimientos m " +
            "WHERE m.detalleComprobanteSalida.producto.id_producto = :productoId AND m.tipo = 'SALIDA'")
    Integer sumSalidasByProductoId(Integer productoId);

    @Query("SELECT m FROM Movimientos m LEFT JOIN m.detalleNotaIngreso dni LEFT JOIN m.detalleComprobanteSalida dcs WHERE (dni.producto.id_producto = :idProducto OR dcs.producto.id_producto = :idProducto) ORDER BY m.idMovimiento")
    List<Movimientos> findMovimientosByProductoId(@Param("idProducto") Integer idProducto);

}
