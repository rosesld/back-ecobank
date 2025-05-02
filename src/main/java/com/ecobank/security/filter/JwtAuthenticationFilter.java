package com.ecobank.security.filter;

import com.ecobank.security.jwt.JwtTokenProvider;
import com.ecobank.security.model.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final UserDetailsService userDetailsService;

    // Constructor actualizado
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    // Rutas excluidas del filtro
    private static final List<String> EXCLUDED_PATHS = List.of(
            "/api/auth/login",
            "/api/vendedor/registro-vendedor",
            "/api/auth/registro-cliente",
            "/api/productos/filtrados-productos",
            "/api/productos/productos"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println("==> Solicitando ruta: " + path);

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

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("==> Autenticación colocada en el SecurityContext.");
            } catch (Exception e) {
                System.out.println("==> Error al procesar el token: " + e.getMessage());
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return (StringUtils.hasText(header) && header.startsWith("Bearer ")) ? header.substring(7) : null;
    }
}
