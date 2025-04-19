package com.ecobank.bank.repository;

import com.ecobank.bank.model.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long> {
    Optional<CuentaBancaria> findByNumeroDeCuenta(String cuentaOrigen);

}
