package com.ecobank.repository.auth;

import com.ecobank.model.auth.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {
}
