package com.almacen.repository;

import com.almacen.model.entity.DetalleComprobanteSalida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleComprobanteSalidaRepository extends JpaRepository<DetalleComprobanteSalida, Integer> {
    List<DetalleComprobanteSalida> findByComprobanteSalidaIdComprobanteSalida(Long idComprobanteSalida);

}
