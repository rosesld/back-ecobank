package com.ecobank.repository.auth;

import com.ecobank.model.auth.HistorialPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialPasswordRepository extends JpaRepository<HistorialPassword, Long> {
}
