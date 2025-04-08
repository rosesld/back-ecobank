package com.ecobank.repository.auth;

import com.ecobank.model.auth.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
