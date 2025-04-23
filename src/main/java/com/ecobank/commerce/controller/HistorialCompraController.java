package com.ecobank.commerce.controller;

import com.ecobank.commerce.dto.response.CompraHistorialResponse;
import com.ecobank.commerce.service.impl.HistorialCompraServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/compras")
public class HistorialCompraController {

    private final HistorialCompraServiceImpl historialCompraServiceImpl;

    public HistorialCompraController(HistorialCompraServiceImpl historialCompraServiceImpl) {
        this.historialCompraServiceImpl = historialCompraServiceImpl;
    }

    @GetMapping("/historial/{usuarioId}")
    public List<CompraHistorialResponse> obtenerHistorialCompras(@PathVariable Long usuarioId) {
        return historialCompraServiceImpl.obtenerHistorialCompras(usuarioId);
    }

}
