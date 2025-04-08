package com.ecobank.repository.commerce;

import com.ecobank.model.commerce.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
