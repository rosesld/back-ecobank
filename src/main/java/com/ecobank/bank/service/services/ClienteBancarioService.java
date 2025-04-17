package com.ecobank.bank.service.services;

import com.ecobank.bank.dto.RegistroClienteBancarioDTO;
import com.ecobank.bank.dto.RegistroClienteBancarioResponse;

public interface ClienteBancarioService {
    RegistroClienteBancarioResponse registroClienteBancario(RegistroClienteBancarioDTO registroClienteBancarioDTO);
}
