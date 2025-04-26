package com.ecobank.auth.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;

    // Inicializa el objeto en un estado limpio y concistente
    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setUsuarioNombre("Luis");
        usuario.setUsuarioApellidoPaterno("Perez");
        usuario.setUsuarioApellidoMaterno("Perez");
        usuario.setUsuarioEmail("lperez@correo.com");
        usuario.setUsuarioPassword("secreto12345");
        usuario.setUsuarioTelefono("123456789");
    }

    // Metodos que aseguran que se estasblescan correctamente las fechas.
    // Simulan lo que hace JPA al guardad la entidad si no viene una fecha, coloca la actual.

    @Test
    public void testPrePersistFechaRegistro() {
        assertNull(usuario.getUsuarioFechaRegistro());
        usuario.prePersist();
        assertNotNull(usuario.getUsuarioFechaRegistro());
    }

    @Test
    public void testPreUpdateSetFechaActualizacion() {
        assertNull(usuario.getUsuarioFechaActualizacion());
        usuario.preUpdate();
        assertNotNull(usuario.getUsuarioFechaActualizacion());
    }

    // Metodo para comprobar que los getter & setter esten funcionando correctamente.
    @Test
    public void testGetterAndSetter() {
        assertEquals("Luis", usuario.getUsuarioNombre());
        assertEquals("Perez", usuario.getUsuarioApellidoPaterno());
        assertEquals("Perez", usuario.getUsuarioApellidoMaterno());
        assertEquals("lperez@correo.com", usuario.getUsuarioEmail());
        assertEquals("secreto12345", usuario.getUsuarioPassword());
        assertEquals("123456789", usuario.getUsuarioTelefono());
    }

    // Verifica que se puedan asignar roles a los usuarios correctamente.
    @Test
    public void testRolesInitializacion() {
        Set<Rol> roles = new HashSet<>();
        Rol rol = new Rol();
        roles.add(rol);

        usuario.setRoles(roles);

        assertEquals(1, usuario.getRoles().size());
        assertTrue(usuario.getRoles().contains(rol));
    }




}
