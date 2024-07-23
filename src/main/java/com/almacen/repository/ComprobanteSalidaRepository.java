package com.almacen.repository;

import com.almacen.model.entity.ComprobanteSalida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobanteSalidaRepository extends JpaRepository<ComprobanteSalida, Integer> {
}
