package com.ecobank.model.commerce;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "direccion_envio")
public class DireccionEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "direccion_envio_id")
    private Integer direccionenvioId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "direccion_calle", nullable = false, length = 200)
    private String calle;

    @Column(name = "direccion_numero")
    private Integer numero;

    @Column(name = "direccion_region_id")
    private Integer regionId;

    @Column(name = "direccion_comuna_id")
    private Integer comunaId;

    @Column(name = "direccion_nota", length = 200)
    private String nota;

    @Column(name = "fecha_actualizacion", nullable = false, updatable = false)
    private LocalDateTime fechaActualizacion;

    public DireccionEnvio() {}

    public DireccionEnvio(Integer usuarioId, String calle, Integer numero,
                          Integer regionId, Integer comunaId, String nota) {
        this.usuarioId = usuarioId;
        this.calle = calle;
        this.numero = numero;
        this.regionId = regionId;
        this.comunaId = comunaId;
        this.nota = nota;
    }

    public Integer getDireccionenvioId() {
        return direccionenvioId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getComunaId() {
        return comunaId;
    }

    public void setComunaId(Integer comunaId) {
        this.comunaId = comunaId;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DireccionEnvio{");
        sb.append("direccionenvioId=").append(direccionenvioId);
        sb.append(", usuarioId=").append(usuarioId);
        sb.append(", calle='").append(calle).append('\'');
        sb.append(", numero=").append(numero);
        sb.append(", regionId=").append(regionId);
        sb.append(", comunaId=").append(comunaId);
        sb.append(", nota='").append(nota).append('\'');
        sb.append(", fechaActualizacion=").append(fechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}

