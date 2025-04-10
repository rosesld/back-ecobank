package com.ecobank.commerce.service.impl;

import com.ecobank.auth.model.Rol;
import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.repository.RolRepository;
import com.ecobank.auth.repository.UsuarioRepository;
import com.ecobank.commerce.model.Vendedor;
import com.ecobank.commerce.repository.VendedorRepository;
import com.ecobank.commerce.service.services.VendedorService;
import org.springframework.stereotype.Service;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public VendedorServiceImpl(VendedorRepository vendedorRepository, UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.vendedorRepository = vendedorRepository;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public Vendedor saveVendedor(Vendedor vendedor) {
        if(vendedor.getVendedorRutPyme().isEmpty()) {
            throw new IllegalArgumentException("El rut no puede estar vacio");
        }
        if(vendedor.getVendedorRazonSocial().isEmpty()) {
            throw new IllegalArgumentException("La razon social no puede estar vacia");
        }

        Usuario usuario = vendedor.getUsuario();

        if(usuario.getUsuarioId() == null){
            if(usuario.getUsuarioNombre().isEmpty()){
                throw new IllegalArgumentException("El nombre no puede estar vacia");
            }
            if(usuario.getUsuarioApellidoPaterno().isEmpty()){
                throw new IllegalArgumentException("El apellido paterno no puede estar vacia");
            }
            if(usuario.getUsuarioApellidoMaterno().isEmpty()){
                throw new IllegalArgumentException("El apellido materno no puede estar vacia");
            }
            if(usuario.getUsuarioEmail().isEmpty()){
                throw new IllegalArgumentException("El mail no puede estar vacia");
            }
            if(usuario.getUsuarioPassword().isEmpty()){
                throw new IllegalArgumentException("El password no puede estar vacia");
            }
            usuario = usuarioRepository.save(usuario);

            Rol rolVendedor = rolRepository.findByRolNombre("VENDEDOR");
            if(rolVendedor == null) {
                throw new IllegalArgumentException("El rol no existe");
            }
            usuario.getRoles().add(rolVendedor);
            usuarioRepository.save(usuario);

        } else {
            usuarioRepository.save(usuario);
        }
        vendedor.setUsuario(usuario);
        return vendedorRepository.save(vendedor);
    }
}
