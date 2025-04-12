package com.ecobank.auth.service.services;

import com.ecobank.auth.dto.RegistroVendedorDTO;
import com.ecobank.auth.model.Usuario;

public interface RegistroUsuario {
    Usuario registrarVendedor(RegistroVendedorDTO registroVendedorDTO);


}
