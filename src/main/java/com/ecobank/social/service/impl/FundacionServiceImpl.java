package com.ecobank.social.service.impl;

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
        if(!fundacion.getNombreFundacion().isEmpty() && !fundacion.getDescripcionFundacion().isEmpty()){
            return fundacionRepository.save(fundacion);
        } else {
            throw new IllegalArgumentException("NO SE INGRESARON LOS DATOS CORRESPONDIENTES");
        }
    }

    @Override
    public void deleteFundacion(Long id) {
        fundacionRepository.deleteById(id);
    }

    @Override
    public List<Fundacion> allFundacion() {
        return fundacionRepository.findAll();
    }

    @Override
    public Fundacion findByIdFundacion(Long id) {
        return fundacionRepository.findById(id).
                orElseThrow( () -> new RuntimeException("FUNDACION NO ENCONTRADA"));
    }

    @Override
    public Fundacion updateFundacion(Long id, Fundacion fundacion) {
        Fundacion fundacionExistente = fundacionRepository.findById(id).orElseThrow( () -> new RuntimeException("NO EXISTE UNA FUNDACION CON ID "+ id));
        fundacionExistente.setNombreFundacion(fundacion.getNombreFundacion());
        fundacionExistente.setDescripcionFundacion(fundacion.getDescripcionFundacion());
        return fundacionRepository.save(fundacionExistente);
    }
}
