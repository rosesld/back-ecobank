package com.ecobank.social.repository;

import com.ecobank.social.model.Fundacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundacionRepository extends JpaRepository<Fundacion, Long> {
}
