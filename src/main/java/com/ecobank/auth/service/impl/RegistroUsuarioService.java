package com.ecobank.auth.service.impl;

import com.ecobank.auth.dto.RegistroVendedorDTO;
import com.ecobank.auth.model.Rol;
import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.repository.RolRepository;
import com.ecobank.auth.repository.UsuarioRepository;
import com.ecobank.auth.service.services.RegistroUsuario;
import com.ecobank.commerce.model.Vendedor;
import com.ecobank.commerce.repository.VendedorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistroUsuarioService implements RegistroUsuario {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final VendedorRepository vendedorRepository;

    public RegistroUsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, VendedorRepository vendedorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    @Transactional
    public Usuario registrarVendedor(RegistroVendedorDTO registroVendedorDTO){
        Usuario usuario = new Usuario();
        usuario.setUsuarioNombre(registroVendedorDTO.getNombre());
        usuario.setUsuarioApellidoPaterno(registroVendedorDTO.getApellidoPaterno());
        usuario.setUsuarioApellidoMaterno(registroVendedorDTO.getApellidoMaterno());
        usuario.setUsuarioEmail(registroVendedorDTO.getEmail());
        usuario.setUsuarioPassword(passwordEncoder.encode(registroVendedorDTO.getPassword()));
        usuario.setUsuarioTelefono(registroVendedorDTO.getTelefono());

        Rol rol = rolRepository.findByRolNombre("VENDEDOR");
        if(rol == null) {
            throw new RuntimeException("ROL VENDEDOR NO ENCONTRADO");
        }

        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        usuario.setRoles(roles);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        Vendedor vendedor = new Vendedor();
        vendedor.setVendedorRutPyme(registroVendedorDTO.getRutPyme());
        vendedor.setVendedorRazonSocial(registroVendedorDTO.getRazonSocial());
        vendedor.setUsuario(usuarioGuardado);

        vendedorRepository.save(vendedor);
        return usuarioGuardado;
    }

}
