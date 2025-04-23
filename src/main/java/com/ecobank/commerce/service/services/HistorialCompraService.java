package com.ecobank.commerce.service.services;

import com.ecobank.commerce.dto.response.CompraHistorialResponse;

import java.util.List;

public interface HistorialCompraService {
    List<CompraHistorialResponse> obtenerHistorialCompras(Long usuarioId);
}
