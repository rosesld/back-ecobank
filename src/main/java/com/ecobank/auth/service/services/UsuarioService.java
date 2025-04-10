package com.ecobank.auth.service.services;

import com.ecobank.auth.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    // en la interface se declaran los metodos(es un contrato)
    // metodo guardar, guardaremos un Usuario
    Usuario saveUsuario(Usuario usuario);

    Usuario updateUsuario(Long id, Usuario usuario);

    void deleteUsuario(Long id);

    Usuario findByIdUsuario(Long id);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    List<Usuario> findAllUsuarios();
}
