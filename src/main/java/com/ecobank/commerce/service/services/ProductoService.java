package com.ecobank.commerce.service.services;

import com.ecobank.commerce.dto.RegistroProductoDTO;
import com.ecobank.commerce.dto.RegistroProductoResponse;
import com.ecobank.commerce.model.Producto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductoService {

    RegistroProductoResponse saveProducto(RegistroProductoDTO dto, List<MultipartFile> archivos);
}
