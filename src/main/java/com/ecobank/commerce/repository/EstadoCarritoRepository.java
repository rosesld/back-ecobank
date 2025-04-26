package com.ecobank.commerce.repository;

import com.ecobank.commerce.model.EstadoCarrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoCarritoRepository extends JpaRepository<EstadoCarrito, Long> {
    Optional<EstadoCarrito> findByNombre(String nombre);
}
