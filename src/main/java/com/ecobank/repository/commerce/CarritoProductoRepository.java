package com.ecobank.repository.commerce;

import com.ecobank.model.commerce.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
}
