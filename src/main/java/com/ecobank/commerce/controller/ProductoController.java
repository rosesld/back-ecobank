package com.ecobank.commerce.controller;


import com.ecobank.commerce.dto.request.RegistroProductoDTO;
import com.ecobank.commerce.dto.response.RegistroProductoResponse;
import com.ecobank.commerce.service.impl.ProductoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin
public class ProductoController {

    private final ProductoServiceImpl productoServiceImpl;

    public ProductoController(ProductoServiceImpl productoServiceImpl) {
        this.productoServiceImpl = productoServiceImpl;
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RegistroProductoResponse> crearProducto(@RequestPart("producto") RegistroProductoDTO dto,
                                                                  @RequestPart(value = "imagenes", required = false)List<MultipartFile> imagenes){
        RegistroProductoResponse response = productoServiceImpl.saveProducto(dto, imagenes != null ? imagenes : List.of());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
