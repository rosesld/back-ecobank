package com.ecobank.commerce.service.impl;

import com.ecobank.commerce.dto.response.CategoriaDTO;
import com.ecobank.commerce.model.Categoria;
import com.ecobank.commerce.repository.CategoriaRepository;
import com.ecobank.commerce.service.services.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaDTO> allCategoria() {
        List<Categoria> categorias = categoriaRepository.findAll(); // Obtener todas las categorías de la base de datos
        List<CategoriaDTO> categoriaDTOs = new ArrayList<>();

        for (Categoria categoria : categorias) {
            CategoriaDTO categoriaDTO = new CategoriaDTO(
                    categoria.getCategoriaId(),
                    categoria.getCategoriaNombre(),
                    categoria.getCategoriaDescripcion()
            );
            categoriaDTOs.add(categoriaDTO);
        }

        return categoriaDTOs;
    }
}
