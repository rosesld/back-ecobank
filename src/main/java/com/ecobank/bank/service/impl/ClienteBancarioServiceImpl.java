package com.ecobank.bank.service.impl;

import com.ecobank.auth.model.Rol;
import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.repository.RolRepository;
import com.ecobank.auth.repository.UsuarioRepository;
import com.ecobank.bank.dto.request.RegistroClienteBancarioDTO;
import com.ecobank.bank.dto.response.RegistroClienteBancarioResponse;
import com.ecobank.bank.mapper.ClienteBancarioMapper;
import com.ecobank.bank.model.ClienteBancario;
import com.ecobank.bank.model.CuentaBancaria;
import com.ecobank.bank.model.Estado;
import com.ecobank.bank.model.TipoCuentaEnum;
import com.ecobank.bank.repository.ClienteBancarioRepository;
import com.ecobank.bank.repository.CuentaBancariaRepository;
import com.ecobank.bank.service.services.ClienteBancarioService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class ClienteBancarioServiceImpl implements ClienteBancarioService {

    private final ClienteBancarioRepository clienteBancarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoServiceImpl estadoServiceImpl;
    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final ClienteBancarioMapper clienteBancarioMapper;

    public ClienteBancarioServiceImpl(ClienteBancarioRepository clienteBancarioRepository, PasswordEncoder passwordEncoder, RolRepository rolRepository, UsuarioRepository usuarioRepository, EstadoServiceImpl estadoServiceImpl, CuentaBancariaRepository cuentaBancariaRepository, ClienteBancarioMapper clienteBancarioMapper) {
        this.clienteBancarioRepository = clienteBancarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.estadoServiceImpl = estadoServiceImpl;
        this.cuentaBancariaRepository = cuentaBancariaRepository;
        this.clienteBancarioMapper = clienteBancarioMapper;
    }

    @Override
    @Transactional
    public RegistroClienteBancarioResponse registroClienteBancario(RegistroClienteBancarioDTO registroClienteBancarioDTO) {
        if (clienteBancarioRepository.existsByUsuarioUsuarioEmail(registroClienteBancarioDTO.getEmail())) {
            throw new IllegalArgumentException("El usuario ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setUsuarioNombre(registroClienteBancarioDTO.getNombre());
        usuario.setUsuarioApellidoPaterno(registroClienteBancarioDTO.getApellidoPaterno());
        usuario.setUsuarioApellidoMaterno(registroClienteBancarioDTO.getApellidoMaterno());
        usuario.setUsuarioEmail(registroClienteBancarioDTO.getEmail());
        usuario.setUsuarioPassword(passwordEncoder.encode(registroClienteBancarioDTO.getPassword()));
        usuario.setUsuarioTelefono(registroClienteBancarioDTO.getTelefono());

        Rol rol = rolRepository.findByRolNombre("CLIENTE_BANCARIO");
        if (rol == null) {
            throw new IllegalArgumentException("El rol no existe");
        }
        usuario.setRoles(Set.of(rol));

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        String numeroCuenta = "1818" + registroClienteBancarioDTO.getRutCliente().replace("-", "");
        Estado estadoActivo = estadoServiceImpl.obtenerEstadoActivo();

        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setNumeroDeCuenta(numeroCuenta);
        cuentaBancaria.setSaldo(new BigDecimal("10000"));
        cuentaBancaria.setTipoCuenta(TipoCuentaEnum.VISTA);
        cuentaBancaria.setRequiereActivacion(false);
        cuentaBancaria.setFechaActivacion(LocalDateTime.now());
        cuentaBancaria.setDocumentoIdentidad(registroClienteBancarioDTO.getRutCliente());
        cuentaBancaria.setTelefonoVerificado(false);
        cuentaBancaria.setFechaCreacion(LocalDateTime.now());
        cuentaBancaria.setUsuario(usuarioGuardado);
        cuentaBancaria.setEstado(estadoActivo);

        CuentaBancaria cuentaGuardada = cuentaBancariaRepository.save(cuentaBancaria);

        ClienteBancario clienteBancario = new ClienteBancario();
        clienteBancario.setUsuario(usuarioGuardado);
        clienteBancario.setCuentaBancaria(cuentaGuardada);
        clienteBancario.setRutClienteBancario(registroClienteBancarioDTO.getRutCliente());

        clienteBancarioRepository.save(clienteBancario);

        return clienteBancarioMapper.toResponse(usuarioGuardado, cuentaGuardada);

    }
}
