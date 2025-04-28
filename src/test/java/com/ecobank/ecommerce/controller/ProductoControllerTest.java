package com.ecobank.ecommerce.controller;

import com.ecobank.commerce.controller.ProductoController;
import com.ecobank.commerce.dto.request.RegistroProductoDTO;
import com.ecobank.commerce.dto.response.ProductoPageResponse;
import com.ecobank.commerce.dto.response.RegistroProductoResponse;
import com.ecobank.commerce.service.impl.ProductoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private ProductoServiceImpl productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testCrearProducto() throws Exception {
        // Prepara datos de prueba
        RegistroProductoDTO productoDTO = new RegistroProductoDTO();
        productoDTO.setNombreProducto("Producto Test");
        productoDTO.setPrecioProducto(new BigDecimal("100.00"));
        productoDTO.setStockProducto(10);

        // Mock de la imagen
        MockMultipartFile imagen = new MockMultipartFile(
                "imagenes",
                "test-image.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "test image content".getBytes()
        );

        // Mock del DTO como JSON
        MockMultipartFile productoJson = new MockMultipartFile(
                "producto",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(productoDTO)
        );

        // Mock de la respuesta del servicio
        RegistroProductoResponse mockResponse = new RegistroProductoResponse();
        mockResponse.setProductoId(1L);
        mockResponse.setNombreProducto("Producto Test");

        when(productoService.saveProducto(any(RegistroProductoDTO.class), anyList()))
                .thenReturn(mockResponse);

        // Ejecuta y verifica
        mockMvc.perform(multipart("/api/productos/guardar")
                        .file(productoJson)
                        .file(imagen))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productoId").value(1L))
                .andExpect(jsonPath("$.nombreProducto").value("Producto Test"));
    }

    @Test
    public void testObtenerProductosFiltrados() throws Exception {
        // Prepara respuesta simulada
        ProductoPageResponse mockResponse = new ProductoPageResponse();
        mockResponse.setCurrentPage(0);
        mockResponse.setTotalPages(1);
        mockResponse.setTotalItems(10);

        // Crea lista de productos
        List<RegistroProductoResponse> productos = new ArrayList<>();
        RegistroProductoResponse producto = new RegistroProductoResponse();
        producto.setProductoId(1L);
        producto.setNombreProducto("Producto Test");
        producto.setDescripcionProducto("Descripción Test");
        producto.setPrecioProducto(new BigDecimal("50.00"));
        producto.setStockPorducto(5);

        productos.add(producto);
        mockResponse.setItems(productos);

        when(productoService.listaProductosFiltrados(
                anyString(),
                any(BigDecimal.class),
                any(BigDecimal.class),
                any(Long.class),
                anyInt(),
                anyInt(),
                anyString()))
                .thenReturn(mockResponse);

        // Ejecuta y verifica
        mockMvc.perform(get("/api/productos/filtrados-productos")
                        .param("nombre", "test")
                        .param("precioMin", "10.0")
                        .param("precioMax", "100.0")
                        .param("categoriaId", "1")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "productoNombre,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalItems").value(10))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.items[0].productoId").value(1L))
                .andExpect(jsonPath("$.items[0].nombreProducto").value("Producto Test"))
                .andExpect(jsonPath("$.items[0].descripcionProducto").value("Descripción Test"))
                .andExpect(jsonPath("$.items[0].precioProducto").value(50.00));
                //.andExpect(jsonPath("$.items[0].stockProducto").value(5));
    }
}
