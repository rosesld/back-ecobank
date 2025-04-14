package com.ecobank.auth.controller;

import com.ecobank.auth.dto.RegistroClienteDTO;
import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.service.impl.RegistroUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegistroController {

    private final RegistroUsuarioService registroUsuarioService;

    public RegistroController(RegistroUsuarioService registroUsuarioService) {
        this.registroUsuarioService = registroUsuarioService;
    }

    @PostMapping("/registro-cliente")
    public ResponseEntity<Usuario> registrarCliente(@RequestBody RegistroClienteDTO registroClienteDTO){
        Usuario usuario = registroUsuarioService.registroCliente((registroClienteDTO));
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

}
