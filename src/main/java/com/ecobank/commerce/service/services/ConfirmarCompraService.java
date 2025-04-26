package com.ecobank.commerce.service.services;

import com.ecobank.commerce.dto.response.CarritoResponse;
import com.ecobank.commerce.dto.response.CompraResponse;

public interface ConfirmarCompraService {
   CompraResponse confirmarCompra(Long usuarioId, Long direccionEnvioId);
}
