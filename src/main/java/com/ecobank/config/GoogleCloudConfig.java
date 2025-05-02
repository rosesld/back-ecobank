package com.ecobank.config;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class GoogleCloudConfig {

    @Bean
    public Storage storage() throws IOException {
        // Ruta al archivo de credenciales
        String credentialsPath = "C:/Users/tunombre/Documents/credenciales/clave-gcp.json";

        // Cargar las credenciales desde el archivo
        FileInputStream credentialsStream = new FileInputStream(credentialsPath);
        ServiceAccountCredentials credentials = ServiceAccountCredentials.fromStream(credentialsStream);

        // Configurar el servicio de Storage con las credenciales
        return StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
    }
}
