package com.ecobank.repository.commerce;

import com.ecobank.model.commerce.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
