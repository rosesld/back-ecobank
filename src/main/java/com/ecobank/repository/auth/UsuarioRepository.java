package com.ecobank.repository.auth;

import com.ecobank.model.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
