package com.ecobank.ecommerce.controller;

import com.ecobank.commerce.controller.CarritoController;
import com.ecobank.commerce.dto.response.CarritoResponse;
import com.ecobank.commerce.dto.response.CompraResponse;
import com.ecobank.commerce.service.impl.CarritoCompraServiceImpl;
import com.ecobank.commerce.service.impl.ConfirmarCompraServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarritoController.class)
public class CarritoControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private CarritoCompraServiceImpl carritoService;

    @MockBean
    private ConfirmarCompraServiceImpl confirmarCompraService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    private CarritoResponse crearCarritoResponseMock() {
        CarritoResponse response = new CarritoResponse();
        response.setProductoId(100L);
        response.setEstado("ACTIVO");
        response.setTotal(new BigDecimal("250.00"));
        response.setFechaCreacion(LocalDateTime.now());
        response.setFechaActualizacion(LocalDateTime.now());
        response.setProductos(null); // Simulamos lista vacía o puedes mockear productos si es necesario
        return response;
    }

    @Test
    public void testObtenerCarrito() throws Exception {
        CarritoResponse response = crearCarritoResponseMock();

        when(carritoService.obtenerCarritoActivo(1L)).thenReturn(response);

        mockMvc.perform(get("/api/carrito/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(100L))
                .andExpect(jsonPath("$.estado").value("ACTIVO"))
                .andExpect(jsonPath("$.total").value(250.00));
    }

    @Test
    public void testAgregarProducto() throws Exception {
        CarritoResponse response = crearCarritoResponseMock();

        when(carritoService.agregarProducto(anyLong(), anyLong(), anyInt())).thenReturn(response);

        mockMvc.perform(post("/api/carrito/usuario/1/producto/2")
                        .param("cantidad", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(100L))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
    }

    @Test
    public void testEliminarProducto() throws Exception {
        CarritoResponse response = crearCarritoResponseMock();

        when(carritoService.eliminarProducto(1L, 2L)).thenReturn(response);

        mockMvc.perform(delete("/api/carrito/usuario/1/producto/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(100L));
    }

    @Test
    public void testActualizarCantidad() throws Exception {
        CarritoResponse response = crearCarritoResponseMock();

        when(carritoService.actualizarCantidadProducto(anyLong(), anyLong(), anyInt())).thenReturn(response);

        mockMvc.perform(put("/api/carrito/usuario/1/producto/2")
                        .param("cantidad", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(100L))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
    }

    @Test
    public void testVaciarCarrito() throws Exception {
        CarritoResponse response = crearCarritoResponseMock();

        when(carritoService.vaciarCarrito(1L)).thenReturn(response);

        mockMvc.perform(delete("/api/carrito/usuario/1/vaciar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productoId").value(100L));
    }

    @Test
    public void testConfirmarCompra() throws Exception {
        CompraResponse compraResponse = new CompraResponse();
        //compraResponse.setUsuarioId(1L);
        //compraResponse.setMensaje("Compra realizada con éxito");

        when(confirmarCompraService.confirmarCompra(1L, 10L)).thenReturn(compraResponse);

        mockMvc.perform(post("/api/carrito/usuario/1/confirmar")
                        .param("direccionEnvio", "10"))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.usuarioId").value(1L))
                //.andExpect(jsonPath("$.mensaje").value("Compra realizada con éxito"));
    }
}
