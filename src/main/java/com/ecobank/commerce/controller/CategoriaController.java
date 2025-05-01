package com.ecobank.commerce.controller;

import com.ecobank.commerce.dto.response.CategoriaDTO;
import com.ecobank.commerce.model.Categoria;
import com.ecobank.commerce.service.impl.CategoriaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaServiceImpl categoriaServiceImpl;

    public CategoriaController(CategoriaServiceImpl categoriaServiceImpl) {
        this.categoriaServiceImpl = categoriaServiceImpl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoriaDTO>> obtenerCategorias() {
        try {
            List<CategoriaDTO> categorias = categoriaServiceImpl.allCategoria();
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
