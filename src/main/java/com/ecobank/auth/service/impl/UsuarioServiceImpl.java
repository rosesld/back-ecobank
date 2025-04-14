package com.ecobank.auth.service.impl;

import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.repository.RolRepository;
import com.ecobank.auth.repository.UsuarioRepository;
import com.ecobank.auth.service.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario usuarioActual = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe una usuario con el ID "+ id));

        usuarioActual.setUsuarioEmail(usuario.getUsuarioEmail());
        usuarioActual.setUsuarioTelefono(usuario.getUsuarioTelefono());
        usuarioActual.setUsuarioPassword(usuario.getUsuarioPassword());

        return usuarioRepository.save(usuarioActual);
    }

    @Override
    public void deleteUsuario(Long id) {
        Usuario usuarioActual = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe una usuario con el ID "+ id));

        usuarioRepository.delete(usuarioActual);
    }

    @Override
    public Usuario findByIdUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el usuario con el id " + id));
    }

    @Override
    public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
        if (nombreUsuario.isEmpty()){
            throw new RuntimeException("el nombre de usuario no puede estar vacio");
        }
        return usuarioRepository.findByUsuarioNombre(nombreUsuario);
    }

    @Override
    public List<Usuario> findAllUsuarios() {
         return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findByUsuarioEmail(String email) {
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email no puede estar vacio ");
        }
        return usuarioRepository.findByUsuarioEmail(email);
    }

}
