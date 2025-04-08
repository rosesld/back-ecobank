package com.ecobank.commerce.model;

import com.ecobank.auth.model.Usuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "vendedores")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendedor_id")
    private Long vendedorId;

    @Column(name = "vendedor_rut_pyme")
    private String vendedorRutPyme;

    @Column(name = "vendedor_razon_social")
    private String vendedorRazonSocial;

    @Column(name = "vendedor_representante")
    private String vendedorRepresentante;

    @Column(name = "vendedor_fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "vendedor_fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    //TODO: RELACION CON LA TABLA USUARIO, traer llave foranea
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    public Vendedor(){}

    public Vendedor(Long vendedorId, String vendedorRutPyme, String vendedorRazonSocial, String vendedorRepresentante, LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion) {
        this.vendedorId = vendedorId;
        this.vendedorRutPyme = vendedorRutPyme;
        this.vendedorRazonSocial = vendedorRazonSocial;
        this.vendedorRepresentante = vendedorRepresentante;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public String getVendedorRutPyme() {
        return vendedorRutPyme;
    }

    public void setVendedorRutPyme(String vendedorRutPyme) {
        this.vendedorRutPyme = vendedorRutPyme;
    }

    public String getVendedorRazonSocial() {
        return vendedorRazonSocial;
    }

    public void setVendedorRazonSocial(String vendedorRazonSocial) {
        this.vendedorRazonSocial = vendedorRazonSocial;
    }

    public String getVendedorRepresentante() {
        return vendedorRepresentante;
    }

    public void setVendedorRepresentante(String vendedorRepresentante) {
        this.vendedorRepresentante = vendedorRepresentante;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vendedor{");
        sb.append("vendedorId=").append(vendedorId);
        sb.append(", vendedorRutPyme='").append(vendedorRutPyme).append('\'');
        sb.append(", vendedorRazonSocial='").append(vendedorRazonSocial).append('\'');
        sb.append(", vendedorRepresentante='").append(vendedorRepresentante).append('\'');
        sb.append(", fechaRegistro=").append(fechaRegistro);
        sb.append(", fechaActualizacion=").append(fechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}
