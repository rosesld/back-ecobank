package com.ecobank.commerce.controller;

import com.ecobank.commerce.dto.request.RegistroVendedorDTO;
import com.ecobank.commerce.dto.response.RegistroVendedorResponse;
import com.ecobank.commerce.service.impl.VendedorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {

    private final VendedorServiceImpl vendedorServiceImpl;


    public VendedorController(VendedorServiceImpl vendedorServiceImpl) {
        this.vendedorServiceImpl = vendedorServiceImpl;
    }

    @PostMapping("/registro-vendedor")
    public ResponseEntity<RegistroVendedorResponse> registrarVendedor(@RequestBody RegistroVendedorDTO registroVendedorDTO) {
        RegistroVendedorResponse response = vendedorServiceImpl.registrarVendedor(registroVendedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
