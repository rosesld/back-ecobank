package com.ecobank.ecommerce.model;

import com.ecobank.commerce.model.Categoria;
import com.ecobank.commerce.model.Imagen;
import com.ecobank.commerce.model.Producto;
import com.ecobank.commerce.model.Vendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ProductoTest {

    private Producto producto;

    // Inicializa el obejeto en un estado limpio y concistente
    @BeforeEach
    public void setUp() {
        producto = new Producto();
        producto.setProductoNombre("Peluche");
        producto.setProductoDescripcion("Peluche fabricado en lana suavecito");
        producto.setProductoPrecio(new BigDecimal("9990.0"));
        producto.setProductoStock(10);
        producto.setProductoDescuento(new BigDecimal("10"));
        producto.setProductoFechaCreacion(LocalDateTime.now());
        producto.setProductoFechaActualizacion(LocalDateTime.now());
    }

    // Metodo para comprobar que los getter & setter esten funcionando correctamente.
    @Test
    public void testGettersAndSetters() {
        assertEquals("Peluche", producto.getProductoNombre());
        assertEquals("Peluche fabricado en lana suavecito", producto.getProductoDescripcion());
        assertEquals(new BigDecimal("9990.0"), producto.getProductoPrecio());
        assertEquals(10, producto.getProductoStock());
        assertEquals(new BigDecimal("10"), producto.getProductoDescuento());
        assertNotNull(producto.getProductoFechaCreacion());
        assertNotNull(producto.getProductoFechaActualizacion());
    }

    // Metodo para probar que la lista de imagenes se inicialice y funcione correctamente
    @Test
    public void testImagenesListaIniciacion(){
        Imagen imagen = new Imagen();
        List<Imagen> imagenes = new ArrayList<>();
        imagenes.add(imagen);
        producto.setImagenes(imagenes);

        assertEquals(1, producto.getImagenes().size());
        assertTrue(producto.getImagenes().contains(imagen));
    }

    // Metodo para probar la asignacion de un vendedor correctamente
    @Test
    public void testVendedorAsociado() {
        Vendedor vendedor = new Vendedor();
        producto.setVendedor(vendedor);

        assertEquals(vendedor, producto.getVendedor());
    }

    // Metodo para probar la asignacion de una categoria correctamente
    @Test
    public void testCategoriaAsociado() {
        Categoria categoria = new Categoria();
        producto.setCategoria(categoria);

        assertEquals(categoria, producto.getCategoria());
    }

}
