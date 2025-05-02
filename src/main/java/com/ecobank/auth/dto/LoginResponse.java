package com.ecobank.auth.dto;

import java.util.Set;

public class LoginResponse {

    private String token;

    private String nombreCompleto;

    private String tipo = "Bearer";

    private Long id;

    private String email;

    private Set<String> roles;

    public LoginResponse(String token, String nombreCompleto, String tipo, Long id, String email, Set<String> roles) {
        this.token = token;
        this.nombreCompleto = nombreCompleto;
        this.tipo = tipo;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
