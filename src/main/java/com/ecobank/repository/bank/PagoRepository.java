package com.ecobank.repository.bank;

import com.ecobank.model.bank.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
