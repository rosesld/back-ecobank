package com.ecobank.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class JwtUtils {

    private final String jwtSecret = "clave_super_segura_123456789_ecobank"; // usa la misma clave que en tu JwtTokenProvider

    public Long getUsuarioIdDesdeToken() {
        String token = extractTokenFromContext();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)) // Usa la misma codificación
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("usuarioId", Long.class);
    }

    // Extrae el token directamente desde SecurityContextHolder
    private String extractTokenFromContext() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getCredentials() instanceof String token) {
            return token; // El token ya viene limpio, sin "Bearer "
        }

        throw new RuntimeException("Token JWT no encontrado en SecurityContext");
    }
}
