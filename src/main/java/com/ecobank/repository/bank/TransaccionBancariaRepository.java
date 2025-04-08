package com.ecobank.repository.bank;

import com.ecobank.model.bank.TransaccionBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionBancariaRepository extends JpaRepository<TransaccionBancaria, Long> {
}
