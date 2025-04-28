package com.ecobank.social.service;

import com.ecobank.social.exception.FundacionInvalidDataException;
import com.ecobank.social.exception.FundacionNotFoundException;
import com.ecobank.social.model.Fundacion;
import com.ecobank.social.repository.FundacionRepository;
import com.ecobank.social.service.impl.FundacionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FundacionServiceImplTest {

    @InjectMocks
    private FundacionServiceImpl fundacionService;  // El servicio a probar

    @Mock
    private FundacionRepository fundacionRepository;  // El repositorio simulado

    private Fundacion fundacion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        fundacion = new Fundacion();
        fundacion.setNombreFundacion("Fundacion Test");
        fundacion.setDescripcionFundacion("Descripción de la fundación");
    }

    @Test
    public void testSaveFundacion() {
        when(fundacionRepository.save(any(Fundacion.class))).thenReturn(fundacion);

        Fundacion savedFundacion = fundacionService.saveFundacion(fundacion);

        assertNotNull(savedFundacion);
        assertEquals("Fundacion Test", savedFundacion.getNombreFundacion());
        assertEquals("Descripción de la fundación", savedFundacion.getDescripcionFundacion());
    }

    @Test
    public void testSaveFundacionWithInvalidData() {
        fundacion.setNombreFundacion("");  // Nombre vacío

        FundacionInvalidDataException exception = assertThrows(FundacionInvalidDataException.class, () -> {
            fundacionService.saveFundacion(fundacion);
        });

        assertEquals("El campo nombre no debe estar vacio", exception.getMessage());
    }

    @Test
    public void testDeleteFundacion() {
        when(fundacionRepository.findById(1L)).thenReturn(Optional.of(fundacion));

        fundacionService.deleteFundacion(1L);

        verify(fundacionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindByIdFundacion() {
        when(fundacionRepository.findById(1L)).thenReturn(Optional.of(fundacion));

        Fundacion foundFundacion = fundacionService.findByIdFundacion(1L);

        assertNotNull(foundFundacion);
        assertEquals("Fundacion Test", foundFundacion.getNombreFundacion());
    }

    @Test
    public void testFindByIdFundacionNotFound() {
        when(fundacionRepository.findById(1L)).thenReturn(Optional.empty());

        FundacionNotFoundException exception = assertThrows(FundacionNotFoundException.class, () -> {
            fundacionService.findByIdFundacion(1L);
        });

        assertEquals("No existe fundacion con el ID 1", exception.getMessage());
    }

    @Test
    public void testUpdateFundacion() {
        Fundacion updatedFundacion = new Fundacion();
        updatedFundacion.setNombreFundacion("Fundacion Actualizada");
        updatedFundacion.setDescripcionFundacion("Descripción actualizada");

        when(fundacionRepository.findById(1L)).thenReturn(Optional.of(fundacion));
        when(fundacionRepository.save(any(Fundacion.class))).thenReturn(updatedFundacion);

        Fundacion result = fundacionService.updateFundacion(1L, updatedFundacion);

        assertNotNull(result);
        assertEquals("Fundacion Actualizada", result.getNombreFundacion());
        assertEquals("Descripción actualizada", result.getDescripcionFundacion());
    }

    @Test
    public void testUpdateFundacionNotFound() {
        when(fundacionRepository.findById(1L)).thenReturn(Optional.empty());

        FundacionNotFoundException exception = assertThrows(FundacionNotFoundException.class, () -> {
            fundacionService.updateFundacion(1L, fundacion);
        });

        assertEquals("No existe una fundación con el ID 1", exception.getMessage());
    }
}
