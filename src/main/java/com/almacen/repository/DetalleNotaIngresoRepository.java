package com.almacen.repository;

import com.almacen.model.entity.DetalleNotaIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleNotaIngresoRepository extends JpaRepository<DetalleNotaIngreso, Integer> {
}
