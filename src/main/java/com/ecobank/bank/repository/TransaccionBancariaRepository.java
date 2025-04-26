package com.ecobank.bank.repository;

import com.ecobank.bank.model.CuentaBancaria;
import com.ecobank.bank.model.TransaccionBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionBancariaRepository extends JpaRepository<TransaccionBancaria, Long> {
    List<TransaccionBancaria> findByCuentaOrigenNumeroDeCuenta(String numeroCuenta);
    List<TransaccionBancaria> findByCuentaOrigenOrCuentaDestino(CuentaBancaria origen, CuentaBancaria destino);
}
