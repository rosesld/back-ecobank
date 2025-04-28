package com.ecobank.bank.controller;

import com.ecobank.bank.dto.request.RegistroClienteBancarioDTO;
import com.ecobank.bank.dto.response.RegistroClienteBancarioResponse;
import com.ecobank.bank.service.impl.ClienteBancarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteBancarioController.class)
public class ClienteBancarioControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private ClienteBancarioServiceImpl clienteBancarioServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testRegistroClienteBancario() throws Exception {
        // Prepara los datos de prueba
        RegistroClienteBancarioDTO requestDTO = new RegistroClienteBancarioDTO();
        requestDTO.setNombre("Juan");
        requestDTO.setApellidoPaterno("Pérez");
        requestDTO.setApellidoMaterno("Pérez");
        requestDTO.setRutCliente("12345678");
        requestDTO.setEmail("juan.perez@example.com");
        requestDTO.setTelefono("987654321");
        // Completa otros campos necesarios si tu DTO tiene más atributos

        // Mock de la respuesta esperada
        RegistroClienteBancarioResponse mockResponse = new RegistroClienteBancarioResponse();
        mockResponse.setClienteId(1L);
        mockResponse.setNombre("Juan");
        mockResponse.setApellidoPaterno("Pérez");
        mockResponse.setApellidoMaterno("Pérez");
        mockResponse.setEmail("juan.perez@example.com");
        mockResponse.setTelefono("987654321");
        // Completa otros campos si los necesitas

        // Configura el comportamiento del servicio
        when(clienteBancarioServiceImpl.registroClienteBancario(any(RegistroClienteBancarioDTO.class)))
                .thenReturn(mockResponse);

        // Ejecuta y verifica
        mockMvc.perform(post("/api/auth/bank/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clienteId").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellidoPaterno").value("Pérez"))
                .andExpect(jsonPath("$.apellidoMaterno").value("Pérez"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"))
                .andExpect(jsonPath("$.telefono").value("987654321"));
    }
}
