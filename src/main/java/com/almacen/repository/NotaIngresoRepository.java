package com.almacen.repository;

import com.almacen.model.entity.NotaIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaIngresoRepository extends JpaRepository<NotaIngreso, Integer> {
}
