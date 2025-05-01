package com.ecobank.security.filter;

import com.ecobank.security.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    // Rutas que no deben pasar por el filtro
    private static final List<String> EXCLUDED_PATHS = List.of(
            "/api/auth/login",
            "/api/vendedor/registro-vendedor",
            "/api/productos/filtrados-productos",
            "/api/productos/productos"
    );

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println("==> Solicitando ruta: " + path);

        // Si el path está en la lista de rutas excluidas, pasa al siguiente filtro
        if (EXCLUDED_PATHS.stream().anyMatch(path::startsWith)) {
            System.out.println("==> Ruta excluida del filtro, se omite autenticación.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = getTokenFromRequest(request);

        System.out.println("==> TOKEN RECIBIDO: " + token);

        if (!StringUtils.hasText(token)) {
            System.out.println("==> Token ausente o mal formado");
        }

        boolean tokenValido = jwtTokenProvider.validarToken(token);
        System.out.println("==> TOKEN VÁLIDO: " + tokenValido);

        if (StringUtils.hasText(token) && tokenValido) {
            try {
                String email = jwtTokenProvider.obtenerEmailDesdeToken(token);
                List<String> roles = jwtTokenProvider.obtenerRolesDesdeToken(token);
                System.out.println("==> Email extraído del token: " + email);
                System.out.println("==> Roles extraídos del token: " + roles);

                var authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, token, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("==> Autenticación colocada en el SecurityContext.");
            } catch (Exception e) {
                System.out.println("==> Error al procesar el token: " + e.getMessage());
                e.printStackTrace(); // Opcional: muestra el stack completo en consola
            }
        }

        // Continuar con el filtro
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return (StringUtils.hasText(header) && header.startsWith("Bearer ")) ? header.substring(7) : null;
    }
}