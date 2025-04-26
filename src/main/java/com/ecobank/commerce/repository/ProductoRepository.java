package com.ecobank.commerce.repository;

import com.ecobank.commerce.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p " +
            "WHERE (:nombre IS NULL OR LOWER(p.productoNombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:precioMin IS NULL OR p.productoPrecio >= :precioMin) " +
            "AND (:precioMax IS NULL OR p.productoPrecio <= :precioMax) " +
            "AND (:categoriaId IS NULL OR p.categoria.id = :categoriaId) " +
            "AND p.productoStock >= 0")
    Page<Producto> buscarConFiltros(
            @Param("nombre") String nombre,
            @Param("precioMin") BigDecimal precioMin,
            @Param("precioMax") BigDecimal precioMax,
            @Param("categoriaId") Long categoriaId,
            Pageable pageable
    );
}
