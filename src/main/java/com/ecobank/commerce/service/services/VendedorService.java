package com.ecobank.commerce.service.services;

import com.ecobank.commerce.dto.request.RegistroVendedorDTO;
import com.ecobank.commerce.dto.response.RegistroVendedorResponse;

public interface VendedorService {

    RegistroVendedorResponse registrarVendedor(RegistroVendedorDTO registroVendedorDTO);

}
