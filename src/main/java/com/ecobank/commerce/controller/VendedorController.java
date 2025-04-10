package com.ecobank.commerce.controller;

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

    @PostMapping("/guardar")
    public ResponseEntity<Vendedor> saveVendedor(@RequestBody Vendedor vendedor){
        return new ResponseEntity<Vendedor>(vendedorServiceImpl.saveVendedor(vendedor), HttpStatus.CREATED);
    }
}
