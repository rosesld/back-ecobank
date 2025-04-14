package com.ecobank.commerce.service.services;

import com.ecobank.commerce.dto.RegistroVendedorDTO;
import com.ecobank.auth.model.Usuario;

public interface VendedorService {

    // Guardar un vendedor
    Usuario registrarVendedor(RegistroVendedorDTO registroVendedorDTO);


}
