package com.ecobank.bank.model;

import com.ecobank.auth.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ClienteBancarioTest {

    private ClienteBancario clienteBancario;
    private Usuario mockUsuario;
    private CuentaBancaria mockCuenta;

    @BeforeEach
    void setUp() {
        mockUsuario = mock(Usuario.class);
        mockCuenta = mock(CuentaBancaria.class);

        clienteBancario = new ClienteBancario();
        clienteBancario.setUsuario(mockUsuario);
        clienteBancario.setCuentaBancaria(mockCuenta);
        clienteBancario.setRutClienteBancario("12345678-9");
    }

    @Test
    void testPrePersist_setsFechaRegistroIfNull() {
        assertNull(clienteBancario.getFechaRegistro());

        clienteBancario.prePersist();

        assertNotNull(clienteBancario.getFechaRegistro());
    }

    @Test
    void testPreUpdate_setsFechaActualizacion() {
        assertNull(clienteBancario.getFechaActualizacion());

        clienteBancario.preUpdate();

        assertNotNull(clienteBancario.getFechaActualizacion());
    }

    @Test
    void testSettersAndGetters() {
        LocalDateTime now = LocalDateTime.now();

        clienteBancario.setFechaRegistro(now);
        clienteBancario.setFechaActualizacion(now);

        assertEquals("12345678-9", clienteBancario.getRutClienteBancario());
        assertEquals(mockUsuario, clienteBancario.getUsuario());
        assertEquals(mockCuenta, clienteBancario.getCuentaBancaria());
        assertEquals(now, clienteBancario.getFechaRegistro());
        assertEquals(now, clienteBancario.getFechaActualizacion());
    }

    @Test
    void testToString_doesNotThrowException() {
        assertDoesNotThrow(() -> clienteBancario.toString());
    }
}
