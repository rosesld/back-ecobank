package com.ecobank.auth.security;

import com.ecobank.auth.model.Usuario;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String jwtSecret = "clave_super_segura_123456789_ecobank";
    private final long jwtExpirationInMinutes = 86400000;
    private final Key jwtSecretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    public String generarToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("usuarioId", userPrincipal.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMinutes))
                .signWith(jwtSecretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String obtenerEmailDesdeToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecretKey).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validarToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey).build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
