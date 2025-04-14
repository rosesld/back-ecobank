package com.ecobank.commerce.controller;

import com.ecobank.commerce.dto.RegistroVendedorDTO;
import com.ecobank.auth.model.Usuario;
import com.ecobank.commerce.model.Vendedor;
import com.ecobank.commerce.service.impl.VendedorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {

    private final VendedorServiceImpl vendedorServiceImpl;


    public VendedorController(VendedorServiceImpl vendedorServiceImpl) {
        this.vendedorServiceImpl = vendedorServiceImpl;
    }

    @PostMapping("/registro-vendedor")
    public ResponseEntity<?> registrarVendedor(@RequestBody RegistroVendedorDTO registroVendedorDTO) {
        Usuario usuario = vendedorServiceImpl.registrarVendedor(registroVendedorDTO);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
}
