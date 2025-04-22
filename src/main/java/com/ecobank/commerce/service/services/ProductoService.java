package com.ecobank.commerce.service.services;

import com.ecobank.commerce.dto.request.RegistroProductoDTO;
import com.ecobank.commerce.dto.response.RegistroProductoResponse;
import com.ecobank.commerce.model.Producto;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService {

    RegistroProductoResponse saveProducto(RegistroProductoDTO dto, List<MultipartFile> archivos);
    List<RegistroProductoResponse> listaProductos();
    List<RegistroProductoResponse> buscarProductoPorNombre(String nombreProducto);
    List<RegistroProductoResponse> buscarPorPrecio(BigDecimal min, BigDecimal max);
}
