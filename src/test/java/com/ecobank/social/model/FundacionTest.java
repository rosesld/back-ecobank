package com.ecobank.social.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FundacionTest {

    private Fundacion fundacion;

    // Inicializa el objeto en un estado limpio y consistente
    @BeforeEach
    public void setUp() {
        fundacion = new Fundacion();
        fundacion.setNombreFundacion("Fundacion Esperanza");
        fundacion.setDescripcionFundacion("Fundación dedicada a apoyar a comunidades en riesgo.");
    }

    // Método para comprobar que los getters y setters estén funcionando correctamente
    @Test
    public void testGettersAndSetters() {
        assertEquals("Fundacion Esperanza", fundacion.getNombreFundacion());
        assertEquals("Fundación dedicada a apoyar a comunidades en riesgo.", fundacion.getDescripcionFundacion());
    }

    // Método para probar que la inicialización de los valores funciona correctamente
    @Test
    public void testFundacionInitialization() {
        assertNotNull(fundacion.getNombreFundacion());
        assertNotNull(fundacion.getDescripcionFundacion());
    }

    // Método para probar la asignación de datos
    @Test
    public void testAsignacionDatos() {
        fundacion.setNombreFundacion("Nueva Fundación");
        fundacion.setDescripcionFundacion("Descripción de la nueva fundación.");

        assertEquals("Nueva Fundación", fundacion.getNombreFundacion());
        assertEquals("Descripción de la nueva fundación.", fundacion.getDescripcionFundacion());
    }

    // Método para verificar que la fundación tiene el ID asignado correctamente
    @Test
    public void testFundacionId() {
        fundacion.setFundacionId(1L);
        assertEquals(1L, fundacion.getFundacionId());
    }

    // Método para verificar el toString()
    @Test
    public void testToString() {
        fundacion.setFundacionId(1L);
        fundacion.setNombreFundacion("Fundacion Esperanza");
        fundacion.setDescripcionFundacion("Fundación dedicada a apoyar a comunidades en riesgo.");

        String expectedToString = "Fundacion{FundacionId=1, nombreFundacion='Fundacion Esperanza', descripcionFundacion='Fundación dedicada a apoyar a comunidades en riesgo.'}";
        assertEquals(expectedToString, fundacion.toString());
    }
}
