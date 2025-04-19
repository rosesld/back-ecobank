package com.ecobank.bank.service.services;

import com.ecobank.bank.dto.request.RegistroClienteBancarioDTO;
import com.ecobank.bank.dto.response.RegistroClienteBancarioResponse;

public interface ClienteBancarioService {
    RegistroClienteBancarioResponse registroClienteBancario(RegistroClienteBancarioDTO registroClienteBancarioDTO);
}
