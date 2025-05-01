package com.ecobank.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtUtils {

    private final String jwtSecret = "clave_super_segura_123456789_ecobank"; // usa la misma clave que en tu JwtTokenProvider

    public Long getUsuarioIdDesdeToken() {
        String token = extractTokenFromContext();
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims.get("usuarioId", Long.class);
    }

    private String extractTokenFromContext() {
        String authHeader = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        if (authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("Token JWT no encontrado");
    }
}