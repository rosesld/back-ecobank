package com.ecobank.commerce.service.services;

import com.ecobank.commerce.dto.request.RegistroProductoDTO;
import com.ecobank.commerce.dto.response.RegistroProductoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductoService {

    RegistroProductoResponse saveProducto(RegistroProductoDTO dto, List<MultipartFile> archivos);
}
