package com.ecobank.commerce.repository;

import com.ecobank.auth.model.Usuario;
import com.ecobank.bank.model.Estado;
import com.ecobank.commerce.model.CarritoCompra;
import com.ecobank.commerce.model.EstadoCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoCompraRepository extends JpaRepository<CarritoCompra, Long> {
    Optional<CarritoCompra> findByUsuarioAndEstadoCarrito(Usuario usuario, EstadoCarrito estadoCarrito);
}
