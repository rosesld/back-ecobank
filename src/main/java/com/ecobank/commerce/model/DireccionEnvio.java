package com.ecobank.commerce.model;

import com.ecobank.auth.model.Usuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "direccion_envio")
public class DireccionEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "direccion_envio_id")
    private Integer direccionenvioId;

    @Column(name = "direccion_calle", nullable = false, length = 200)
    private String calle;

    @Column(name = "direccion_numero")
    private Integer numero;

    @Column(name = "direccion_nota", length = 200)
    private String nota;

    @Column(name = "fecha_actualizacion", nullable = false, updatable = false)
    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "direccion_region_id", referencedColumnName = "region_id", nullable = false)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "direccion_comuna_id", referencedColumnName = "comuna_id", nullable = false)
    private Comuna comuna;

    public DireccionEnvio() {}

    public DireccionEnvio(Integer direccionenvioId, String calle, Integer numero, String nota, LocalDateTime fechaActualizacion, Usuario usuario, Region region, Comuna comuna) {
        this.direccionenvioId = direccionenvioId;
        this.calle = calle;
        this.numero = numero;
        this.nota = nota;
        this.fechaActualizacion = fechaActualizacion;
        this.usuario = usuario;
        this.region = region;
        this.comuna = comuna;
    }

    public Integer getDireccionenvioId() {
        return direccionenvioId;
    }

    public void setDireccionenvioId(Integer direccionenvioId) {
        this.direccionenvioId = direccionenvioId;
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DireccionEnvio{");
        sb.append("direccionenvioId=").append(direccionenvioId);
        sb.append(", calle='").append(calle).append('\'');
        sb.append(", numero=").append(numero);
        sb.append(", nota='").append(nota).append('\'');
        sb.append(", fechaActualizacion=").append(fechaActualizacion);
        sb.append(", usuario=").append(usuario);
        sb.append(", region=").append(region);
        sb.append(", comuna=").append(comuna);
        sb.append('}');
        return sb.toString();
    }
}

