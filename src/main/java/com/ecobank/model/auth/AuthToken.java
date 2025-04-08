package com.ecobank.model.auth;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = ("auth_tokens"))
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long authTokenId;

    @Column(name = "auth_token_token", nullable = false, unique = true)
    private String authTokenToken;

    @Column(name = "auth_token_dispositivo")
    private String authTokenDispositivo;

    @Column(name = "auth_token_fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime authTokenFechaCreacion;

    @Column(name = "auth_token_fecha_expiracion", nullable = false)
    private LocalDateTime authTokenFechaExpiracion;

    @Column(name = "auth_token_esValido", columnDefinition = "boolean default true")
    private Boolean authTokenEsValido;

    // TODO: Relacion con Usuario

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    public AuthToken() {

    }

    public AuthToken(Long authTokenId, String authTokenToken, String authTokenDispositivo, LocalDateTime authTokenFechaCreacion, LocalDateTime authTokenFechaExpiracion, Boolean authTokenEsValido) {
        this.authTokenId = authTokenId;
        this.authTokenToken = authTokenToken;
        this.authTokenDispositivo = authTokenDispositivo;
        this.authTokenFechaCreacion = authTokenFechaCreacion;
        this.authTokenFechaExpiracion = authTokenFechaExpiracion;
        this.authTokenEsValido = authTokenEsValido;
    }

    public Long getAuthTokenId() {
        return authTokenId;
    }

    public String getAuthTokenToken() {
        return authTokenToken;
    }

    public void setAuthTokenToken(String authTokenToken) {
        this.authTokenToken = authTokenToken;
    }

    public String getAuthTokenDispositivo() {
        return authTokenDispositivo;
    }

    public void setAuthTokenDispositivo(String authTokenDispositivo) {
        this.authTokenDispositivo = authTokenDispositivo;
    }

    public LocalDateTime getAuthTokenFechaCreacion() {
        return authTokenFechaCreacion;
    }

    public void setAuthTokenFechaCreacion(LocalDateTime authTokenFechaCreacion) {
        this.authTokenFechaCreacion = authTokenFechaCreacion;
    }

    public LocalDateTime getAuthTokenFechaExpiracion() {
        return authTokenFechaExpiracion;
    }

    public void setAuthTokenFechaExpiracion(LocalDateTime authTokenFechaExpiracion) {
        this.authTokenFechaExpiracion = authTokenFechaExpiracion;
    }

    public Boolean getAuthTokenEsValido() {
        return authTokenEsValido;
    }

    public void setAuthTokenEsValido(Boolean authTokenEsValido) {
        this.authTokenEsValido = authTokenEsValido;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthToken{");
        sb.append("authTokenId=").append(authTokenId);
        sb.append(", authTokenToken='").append(authTokenToken).append('\'');
        sb.append(", authTokenDispositivo='").append(authTokenDispositivo).append('\'');
        sb.append(", authTokenFechaCreacion=").append(authTokenFechaCreacion);
        sb.append(", authTokenFechaExpiracion=").append(authTokenFechaExpiracion);
        sb.append(", authTokenEsValido=").append(authTokenEsValido);
        sb.append('}');
        return sb.toString();
    }
}
