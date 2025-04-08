package com.ecobank.auth.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_passwords")
public class HistorialPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historial_password_id")
    private Long historialPasswordId;

    @Column(name = "password_hash", nullable = false, length = 254)
    private String passwordHash;

    @Column(name = "historial_password_fecha_creacion", nullable = true)
    private LocalDateTime historialPasswordFechaCreacion;

    // TODO: Relacion con Usuario

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    public HistorialPassword(){

    }

    public HistorialPassword(Long historialPasswordId, String passwordHash, LocalDateTime historialPasswordFechaCreacion) {
        this.historialPasswordId = historialPasswordId;
        this.passwordHash = passwordHash;
        this.historialPasswordFechaCreacion = historialPasswordFechaCreacion;
    }

    public Long getHistorialPasswordId() {
        return historialPasswordId;
    }

    public void setHistorialPasswordId(Long historialPasswordId) {
        this.historialPasswordId = historialPasswordId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getHistorialPasswordFechaCreacion() {
        return historialPasswordFechaCreacion;
    }

    public void setHistorialPasswordFechaCreacion(LocalDateTime historialPasswordFechaCreacion) {
        this.historialPasswordFechaCreacion = historialPasswordFechaCreacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HistorialPassword{");
        sb.append("historialPasswordId=").append(historialPasswordId);
        sb.append(", passwordHash='").append(passwordHash).append('\'');
        sb.append(", historialPasswordFechaCreacion=").append(historialPasswordFechaCreacion);
        sb.append('}');
        return sb.toString();
    }
}
