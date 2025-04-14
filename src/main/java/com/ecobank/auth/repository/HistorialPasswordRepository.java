package com.ecobank.auth.repository;

import com.ecobank.auth.model.HistorialPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialPasswordRepository extends JpaRepository<HistorialPassword, Long> {
}
