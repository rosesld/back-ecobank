package com.ecobank.bank.repository;

import com.ecobank.bank.model.TransaccionBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionBancariaRepository extends JpaRepository<TransaccionBancaria, Long> {
}
