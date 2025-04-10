package com.ecobank.social.service.impl;

import com.ecobank.social.exception.FundacionInvalidDataException;
import com.ecobank.social.exception.FundacionNotFoundException;
import com.ecobank.social.model.Fundacion;
import com.ecobank.social.repository.FundacionRepository;
import com.ecobank.social.service.services.FundacionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FundacionServiceImpl implements FundacionService {

    private FundacionRepository fundacionRepository;

    public FundacionServiceImpl(FundacionRepository fundacionRepository) {
        this.fundacionRepository = fundacionRepository;
    }

    @Override
    public Fundacion saveFundacion(Fundacion fundacion) {
        if(fundacion.getNombreFundacion().isEmpty()){
            throw new FundacionInvalidDataException("El campo nombre no debe estar vacio");
        }
        if(fundacion.getDescripcionFundacion().isEmpty()){
            throw new FundacionInvalidDataException("El campo descripción no debe estar vacio");
        }
        return fundacionRepository.save(fundacion);
    }

    @Override
    public void deleteFundacion(Long id) {
        Fundacion fundacionExistente = fundacionRepository.findById(id).orElseThrow( () -> new RuntimeException("NO EXISTE UNA FUNDACION CON ID "+ id));
        if(fundacionExistente != null) {
            fundacionRepository.deleteById(id);
        } else {
            throw new FundacionNotFoundException(("No existe una fundacion con el ID " + id));
        }
    }

    @Override
    public List<Fundacion> allFundacion() {
        return fundacionRepository.findAll();
    }

    @Override
    public Fundacion findByIdFundacion(Long id) {
        return fundacionRepository.findById(id).
                orElseThrow( () -> new FundacionNotFoundException("No existe fundacion con el ID " + id));
    }

    @Override
    public Fundacion updateFundacion(Long id, Fundacion fundacion) {
        Fundacion fundacionExistente = fundacionRepository.findById(id)
                .orElseThrow( () -> new FundacionNotFoundException("No existe una fundación con el ID "+ id));

        fundacionExistente.setNombreFundacion(fundacion.getNombreFundacion());
        fundacionExistente.setDescripcionFundacion(fundacion.getDescripcionFundacion());

        return fundacionRepository.save(fundacionExistente);
    }
}
