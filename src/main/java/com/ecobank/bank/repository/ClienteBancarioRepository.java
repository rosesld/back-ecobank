package com.ecobank.bank.repository;

import com.ecobank.bank.model.ClienteBancario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteBancarioRepository extends JpaRepository<ClienteBancario, Long> {
    boolean existsByUsuarioUsuarioEmail(String email);

}
