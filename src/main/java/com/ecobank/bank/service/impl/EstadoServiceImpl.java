package com.ecobank.bank.service.impl;

import com.ecobank.bank.model.Estado;
import com.ecobank.bank.repository.EstadoRepository;
import com.ecobank.bank.service.services.EstadoService;
import org.springframework.stereotype.Service;

@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoServiceImpl(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Estado obtenerEstadoActivo() {
        return estadoRepository.findByEstadoNombre("ACTIVO").orElseThrow(() -> new IllegalArgumentException("Estado ACTIVO no encontrado"));
    }
}
