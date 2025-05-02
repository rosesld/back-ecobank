package com.ecobank.commerce.service.services;

import org.springframework.web.multipart.MultipartFile;

public interface AlmacenamientoArchivoService {
    String saveFile(MultipartFile file);
}
