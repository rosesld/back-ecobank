package com.ecobank.security.service;

import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.service.impl.UsuarioServiceImpl;
import com.ecobank.security.model.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioServiceImpl usuarioServiceImpl;

    public UserDetailsServiceImpl(UsuarioServiceImpl usuarioServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioServiceImpl.findByUsuarioEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
        usuario.getRoles().size();
        return new UserDetailsImpl(usuario);
    }
}
