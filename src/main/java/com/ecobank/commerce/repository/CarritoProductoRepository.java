package com.ecobank.commerce.repository;

import com.ecobank.commerce.model.CarritoCompra;
import com.ecobank.commerce.model.CarritoProducto;
import com.ecobank.commerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    List<CarritoProducto> findByCarritoCompra(CarritoCompra carrito);
    Optional<CarritoProducto>findByCarritoCompraAndProducto(CarritoCompra carrito, Producto producto);
}
