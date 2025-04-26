package com.ecobank.bank.controller;

import com.ecobank.bank.dto.request.RegistroClienteBancarioDTO;
import com.ecobank.bank.dto.response.RegistroClienteBancarioResponse;
import com.ecobank.bank.service.impl.ClienteBancarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/bank")
public class ClienteBancarioController {

    private final ClienteBancarioServiceImpl clienteBancarioServiceImpl;

    public ClienteBancarioController(ClienteBancarioServiceImpl clienteBancarioServiceImpl) {
        this.clienteBancarioServiceImpl = clienteBancarioServiceImpl;
    }

    @PostMapping("/guardar")
    public ResponseEntity<RegistroClienteBancarioResponse> registroClienteBancario(@RequestBody RegistroClienteBancarioDTO registroClienteBancarioDTO) {
        RegistroClienteBancarioResponse response = clienteBancarioServiceImpl.registroClienteBancario(registroClienteBancarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
