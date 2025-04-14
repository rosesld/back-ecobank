package com.ecobank.commerce.service.impl;

import com.ecobank.auth.model.Rol;
import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.repository.RolRepository;
import com.ecobank.auth.repository.UsuarioRepository;
import com.ecobank.bank.model.ClienteBancario;
import com.ecobank.bank.model.CuentaBancaria;
import com.ecobank.bank.model.Estado;
import com.ecobank.bank.model.TipoCuentaEnum;
import com.ecobank.bank.repository.ClienteBancarioRepository;
import com.ecobank.bank.repository.CuentaBancariaRepository;
import com.ecobank.bank.repository.EstadoRepository;
import com.ecobank.bank.service.impl.EstadoServiceImpl;
import com.ecobank.commerce.dto.RegistroVendedorDTO;
import com.ecobank.commerce.model.Vendedor;
import com.ecobank.commerce.repository.VendedorRepository;
import com.ecobank.commerce.service.services.VendedorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;
    private final EstadoRepository estadoRepository;
    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final EstadoServiceImpl estadoServiceImpl;
    private final ClienteBancarioRepository clienteBancarioRepository;

    public VendedorServiceImpl(VendedorRepository vendedorRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, RolRepository rolRepository, EstadoRepository estadoRepository, CuentaBancariaRepository cuentaBancariaRepository, EstadoServiceImpl estadoServiceImpl, ClienteBancarioRepository clienteBancarioRepository) {
        this.vendedorRepository = vendedorRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
        this.estadoRepository = estadoRepository;
        this.cuentaBancariaRepository = cuentaBancariaRepository;
        this.estadoServiceImpl = estadoServiceImpl;
        this.clienteBancarioRepository = clienteBancarioRepository;
    }

    @Override
    @Transactional
    public Usuario registrarVendedor(RegistroVendedorDTO dto) {
        if (vendedorRepository.existsByUsuarioUsuarioEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya existe en la base de datos");
        }

        // 1. Crear Usuario
        Usuario usuario = new Usuario();
        usuario.setUsuarioNombre(dto.getNombre());
        usuario.setUsuarioApellidoPaterno(dto.getApellidoPaterno());
        usuario.setUsuarioApellidoMaterno(dto.getApellidoMaterno());
        usuario.setUsuarioEmail(dto.getEmail());
        usuario.setUsuarioPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setUsuarioTelefono(dto.getTelefono());

        Rol rol = rolRepository.findByRolNombre("VENDEDOR");
        if (rol == null) throw new RuntimeException("ROL VENDEDOR NO ENCONTRADO");
        usuario.setRoles(Set.of(rol));

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // 2. Crear Vendedor
        Vendedor vendedor = new Vendedor();
        vendedor.setVendedorRutPyme(dto.getRutPyme());
        vendedor.setVendedorRazonSocial(dto.getRazonSocial());
        vendedor.setUsuario(usuarioGuardado);
        vendedorRepository.save(vendedor);

        // 3. Crear y guardar CuentaBancaria (aÃºn sin ClienteBancario)
        String numeroCuenta = "1818" + dto.getRutPyme().replace("-", "");
        Estado estadoActivo = estadoServiceImpl.obtenerEstadoActivo();

        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setNumeroDeCuenta(numeroCuenta);
        cuentaBancaria.setSaldo(BigDecimal.ZERO);
        cuentaBancaria.setTipoCuenta(TipoCuentaEnum.VISTA);
        cuentaBancaria.setRequiereActivacion(false);
        cuentaBancaria.setFechaActivacion(LocalDateTime.now());
        cuentaBancaria.setDocumentoIdentidad(dto.getRutPyme());
        cuentaBancaria.setTelefonoVerificado(true);
        cuentaBancaria.setFechaCreacion(LocalDateTime.now());
        cuentaBancaria.setUsuario(usuarioGuardado);
        cuentaBancaria.setEstado(estadoActivo);

        CuentaBancaria cuentaGuardada = cuentaBancariaRepository.save(cuentaBancaria);

        // 4. Crear ClienteBancario con la cuenta ya guardada
        ClienteBancario clienteBancario = new ClienteBancario();
        clienteBancario.setUsuario(usuarioGuardado);
        clienteBancario.setCuentaBancaria(cuentaGuardada);

        clienteBancarioRepository.save(clienteBancario);

        return usuarioGuardado;
    }

}
