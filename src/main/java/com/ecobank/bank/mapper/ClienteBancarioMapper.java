package com.ecobank.bank.mapper;


import com.ecobank.auth.model.Usuario;
import com.ecobank.bank.dto.RegistroClienteBancarioResponse;
import com.ecobank.bank.model.CuentaBancaria;
import org.springframework.stereotype.Component;

@Component
public class ClienteBancarioMapper {
    public RegistroClienteBancarioResponse toResponse(Usuario usuario, CuentaBancaria cuenta) {
        RegistroClienteBancarioResponse response = new RegistroClienteBancarioResponse();
        response.setClienteId(usuario.getUsuarioId());
        response.setNombre(usuario.getUsuarioNombre());
        response.setApellidoPaterno(usuario.getUsuarioApellidoPaterno());
        response.setApellidoMaterno(usuario.getUsuarioApellidoMaterno());
        response.setEmail(usuario.getUsuarioEmail());
        response.setTelefono(usuario.getUsuarioTelefono());
        response.setNumeroCuenta(cuenta.getNumeroDeCuenta());
        response.setSaldo(cuenta.getSaldo());
        response.setTipoCuenta(cuenta.getTipoCuenta().name());
        response.setFechaRegistro(cuenta.getFechaCreacion());
        return response;
    }
}
