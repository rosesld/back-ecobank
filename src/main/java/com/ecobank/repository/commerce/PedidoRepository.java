package com.ecobank.repository.commerce;

import com.ecobank.model.commerce.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
