package com.ecobank.commerce.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class GuardarArchivoLocalService {

    private final Path rootPath = Paths.get("src/main/resources/static/images/");

    public String saveFile(MultipartFile file) {
        try {
            if (!Files.exists(rootPath)) {
                Files.createDirectories(rootPath);
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = rootPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/images/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar archivo: " + e.getMessage());
        }
    }
}
