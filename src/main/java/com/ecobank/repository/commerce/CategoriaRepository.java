package com.ecobank.repository.commerce;

import com.ecobank.model.commerce.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
