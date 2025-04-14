package com.ecobank.auth.service.impl;

import com.ecobank.auth.dto.RegistroClienteDTO;
import com.ecobank.auth.model.Rol;
import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.repository.RolRepository;
import com.ecobank.auth.repository.UsuarioRepository;
import com.ecobank.auth.service.services.RegistroUsuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegistroUsuarioService implements RegistroUsuario {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroUsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registroCliente(RegistroClienteDTO registroClienteDTO){
        if(usuarioRepository.existsByUsuarioEmail(registroClienteDTO.getEmail())){
            throw new IllegalArgumentException("El correo ya esta registrado");
        }

        Rol rolCliente = rolRepository.findByRolNombre("CLIENTE");
        if(rolCliente == null) {
            throw new IllegalArgumentException("ROL CLIENTE no existe en la base de datos");
        }

        Usuario usuario = new Usuario();
        usuario.setUsuarioNombre(registroClienteDTO.getNombre());
        usuario.setUsuarioApellidoPaterno(registroClienteDTO.getApellidoPaterno());
        usuario.setUsuarioApellidoMaterno(registroClienteDTO.getApellidoMaterno());
        usuario.setUsuarioEmail(registroClienteDTO.getEmail());
        usuario.setUsuarioTelefono(registroClienteDTO.getTelefono());

        if(registroClienteDTO.getPassword() == null || registroClienteDTO.getPassword().isBlank()){
            throw new IllegalArgumentException("El password no puede estar vacio");
        }

        usuario.setUsuarioPassword(passwordEncoder.encode(registroClienteDTO.getPassword()));

        usuario.getRoles().add(rolCliente);

        return usuarioRepository.save(usuario);
    }

}
