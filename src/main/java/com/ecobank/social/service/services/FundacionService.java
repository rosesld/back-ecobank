package com.ecobank.social.service.services;

import com.ecobank.social.model.Fundacion;

import java.util.List;

public interface FundacionService {

    // guardar
    Fundacion saveFundacion(Fundacion fundacion);

    // borrar
    void deleteFundacion(Long id);

    // buscar
    List<Fundacion> allFundacion();

    // buscar por id
    Fundacion findByIdFundacion(Long id);

    // actualizar
    Fundacion updateFundacion(Long id, Fundacion fundacion);

}
