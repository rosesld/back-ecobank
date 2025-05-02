package com.ecobank.commerce.controller;


import com.ecobank.commerce.dto.request.RegistroProductoDTO;
import com.ecobank.commerce.dto.response.ProductoPageResponse;
import com.ecobank.commerce.dto.response.RegistroProductoResponse;
import com.ecobank.commerce.model.Producto;
import com.ecobank.commerce.service.impl.ProductoServiceImpl;
import com.ecobank.security.model.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoServiceImpl productoServiceImpl;

    public ProductoController(ProductoServiceImpl productoServiceImpl) {
        this.productoServiceImpl = productoServiceImpl;
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RegistroProductoResponse> crearProducto(@RequestPart("producto") RegistroProductoDTO dto,
                                                                  @RequestPart(value = "imagenes", required = false) List<MultipartFile> imagenes,
                                                                  @AuthenticationPrincipal UserDetailsImpl usuarioAutenticado) {
        // Log de los datos recibidos
        System.out.println("Producto: " + dto);
        if (imagenes != null) {
            imagenes.forEach(image -> System.out.println("Imagen: " + image.getOriginalFilename()));
        }

        // Verifica si las imágenes están presentes
        System.out.println("Número de imágenes recibidas: " + (imagenes != null ? imagenes.size() : 0));
        for (MultipartFile imagen : imagenes) {
            System.out.println("Nombre de imagen: " + imagen.getOriginalFilename());
        }

        RegistroProductoResponse response = productoServiceImpl.saveProducto(dto, imagenes != null ? imagenes : List.of(), usuarioAutenticado.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/filtrados-productos")
    public ProductoPageResponse obtenerProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "productoNombre,asc") String sort
    ) {
        return productoServiceImpl.listaProductosFiltrados(nombre, precioMin, precioMax, categoriaId, page, size, sort);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<RegistroProductoResponse> obtenerProductoPorId(@PathVariable Long id) {
        RegistroProductoResponse producto = productoServiceImpl.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    @PreAuthorize("hasRole('VENDEDOR')")
    @GetMapping("/mis-productos")
    public ResponseEntity<List<RegistroProductoResponse>> obtenerMisProductos() {
        List<RegistroProductoResponse> productos = productoServiceImpl.obtenerProductosDelVendedorAutenticado();
        return ResponseEntity.ok(productos);
    }
}
