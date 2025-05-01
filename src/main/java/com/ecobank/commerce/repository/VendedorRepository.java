package com.ecobank.commerce.repository;

import com.ecobank.auth.model.Usuario;
import com.ecobank.commerce.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Boolean existsByUsuarioUsuarioEmail(String email);
    Optional<Vendedor> findByUsuarioUsuarioId(Long usuarioId);
}
