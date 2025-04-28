package com.ecobank.auth.controller;

import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.service.impl.UsuarioServiceImpl;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        // Datos de prueba
        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setUsuarioNombre("Usuario Actualizado");
        usuarioActualizado.setUsuarioEmail("actualizado@example.com");

        when(usuarioServiceImpl.updateUsuario(eq(1L), any(Usuario.class)))
                .thenReturn(usuarioActualizado);

        mockMvc.perform(put("/api/usuarios/actualizar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioActualizado)))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.usuarioId").value(1L))
                .andExpect(jsonPath("$.usuarioNombre").value("Usuario Actualizado"))
                .andExpect(jsonPath("$.usuarioEmail").value("actualizado@example.com"));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioServiceImpl).deleteUsuario(1L);

        mockMvc.perform(delete("/api/usuarios/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("El usuario ha sido eliminado"));
    }

    @Test
    public void testFindByIdUsuario() throws Exception {
        Usuario usuario = new Usuario();
        //usuario.setId(1L);
        usuario.setUsuarioNombre("Usuario Test");
        usuario.setUsuarioEmail("usuario@example.com");

        when(usuarioServiceImpl.findByIdUsuario(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/buscar/1"))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.usuarioId").value(1L))
                .andExpect(jsonPath("$.usuarioNombre").value("Usuario Test"))
                .andExpect(jsonPath("$.usuarioEmail").value("usuario@example.com"));
    }

    @Test
    public void testFindAllUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario1 = new Usuario();

        usuario1.setUsuarioNombre("Usuario Uno");
        usuario1.setUsuarioEmail("uno@example.com");

        Usuario usuario2 = new Usuario();
        usuario2.setUsuarioNombre("Usuario Dos");
        usuario2.setUsuarioEmail("dos@example.com");

        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioServiceImpl.findAllUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios/lista-usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].usuarioNombre").value("Usuario Uno"))
                .andExpect(jsonPath("$[1].usuarioNombre").value("Usuario Dos"));
    }

    @Test
    public void testFindByNombreUsuarioFound() throws Exception {
        Usuario usuario = new Usuario();
        //usuario.setId(1L);
        usuario.setUsuarioNombre("UsuarioNombre");
        usuario.setUsuarioEmail("nombre@example.com");

        when(usuarioServiceImpl.findByNombreUsuario("UsuarioNombre"))
                .thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/nombre/UsuarioNombre"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioNombre").value("UsuarioNombre"))
                .andExpect(jsonPath("$.usuarioEmail").value("nombre@example.com"));
    }

    @Test
    public void testFindByNombreUsuarioNotFound() throws Exception {
        when(usuarioServiceImpl.findByNombreUsuario("Inexistente"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/usuarios/nombre/Inexistente"))
                .andExpect(status().isNotFound());
    }
}
