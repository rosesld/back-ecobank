package com.ecobank.commerce.util;

import com.ecobank.commerce.service.services.AlmacenamientoArchivoService;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service("almacenamientoGCSService")
public class GuardarArchivoGCSService implements AlmacenamientoArchivoService {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();
    private final String bucketName = "ecomarket-backend"; // Reemplaza con tu bucket

    @Override
    public String saveFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                    .setContentType(file.getContentType())
                    .build();

            storage.create(blobInfo, file.getBytes());

            return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar archivo en GCS: " + e.getMessage(), e);
        }
    }
}

