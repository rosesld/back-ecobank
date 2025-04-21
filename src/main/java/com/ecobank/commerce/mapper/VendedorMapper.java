package com.ecobank.commerce.mapper;

import com.ecobank.auth.model.Usuario;
import com.ecobank.bank.model.CuentaBancaria;
import com.ecobank.commerce.dto.response.RegistroVendedorResponse;
import com.ecobank.commerce.model.Vendedor;

public class VendedorMapper {
    public static RegistroVendedorResponse toDto(Usuario usuario, Vendedor vendedor, CuentaBancaria cuenta) {
        RegistroVendedorResponse dto = new RegistroVendedorResponse();

        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNombre(usuario.getUsuarioNombre());
        dto.setApellidoPaterno(usuario.getUsuarioApellidoPaterno());
        dto.setApellidoMaterno(usuario.getUsuarioApellidoMaterno());
        dto.setEmail(usuario.getUsuarioEmail());
        dto.setTelefono(usuario.getUsuarioTelefono());
        dto.setFechaRegistro(usuario.getUsuarioFechaRegistro());

        dto.setRutPyme(vendedor.getVendedorRutPyme());
        dto.setRazonSocial(vendedor.getVendedorRazonSocial());

        dto.setNumeroCuenta(cuenta.getNumeroDeCuenta());
        dto.setSaldo(cuenta.getSaldo());
        dto.setTipoCuenta(cuenta.getTipoCuenta().toString());
        dto.setRequiereActivacion(cuenta.getRequiereActivacion());
        dto.setFechaActivacion(cuenta.getFechaActivacion());

        return dto;
    }

}
