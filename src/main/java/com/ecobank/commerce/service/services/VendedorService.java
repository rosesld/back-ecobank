package com.ecobank.commerce.service.services;

import com.ecobank.commerce.dto.RegistroVendedorDTO;
import com.ecobank.auth.model.Usuario;
import com.ecobank.commerce.dto.RegistroVendedorResponse;

public interface VendedorService {

    RegistroVendedorResponse registrarVendedor(RegistroVendedorDTO registroVendedorDTO);

}
