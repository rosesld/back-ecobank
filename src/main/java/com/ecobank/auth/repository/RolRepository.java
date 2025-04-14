package com.ecobank.auth.repository;

import com.ecobank.auth.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByRolNombre(String rolNombre);
}
