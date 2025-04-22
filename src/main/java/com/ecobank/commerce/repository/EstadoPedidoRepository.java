package com.ecobank.commerce.repository;

import com.ecobank.commerce.model.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Long> {
    Optional<EstadoPedido> findByNombre(String nombre);
}
