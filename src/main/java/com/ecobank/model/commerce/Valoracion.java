package com.ecobank.model.commerce;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "valoraciones")
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valoracion_id")
    private Long valoracionId;

    @Column(name = "valoracion_puntuacion", nullable = false)
    private BigDecimal valoracionPuntuacion;

    @Column(name = "valoracion_comentario", length = 500, nullable = true)
    private String valoracionComentario;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = true)
    private LocalDateTime fechaActualizacion;

    public Valoracion() {
    }

    public Valoracion(Long valoracionId, BigDecimal valoracionPuntuacion, String valoracionComentario,
                      LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.valoracionId = valoracionId;
        this.valoracionPuntuacion = valoracionPuntuacion;
        this.valoracionComentario = valoracionComentario;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getValoracionId() {
        return valoracionId;
    }

    public void setValoracionId(Long valoracionId) {
        this.valoracionId = valoracionId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getValoracionComentario() {
        return valoracionComentario;
    }

    public void setValoracionComentario(String valoracionComentario) {
        this.valoracionComentario = valoracionComentario;
    }

    public BigDecimal getValoracionPuntuacion() {
        return valoracionPuntuacion;
    }

    public void setValoracionPuntuacion(BigDecimal valoracionPuntuacion) {
        this.valoracionPuntuacion = valoracionPuntuacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Valoracion{");
        sb.append("valoracionId=").append(valoracionId);
        sb.append(", valoracionPuntuacion=").append(valoracionPuntuacion);
        sb.append(", valoracionComentario='").append(valoracionComentario).append('\'');
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", fechaActualizacion=").append(fechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}

