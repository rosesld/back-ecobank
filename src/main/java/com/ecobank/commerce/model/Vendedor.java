package com.ecobank.commerce.model;

import com.ecobank.auth.model.Usuario;
import com.ecobank.bank.model.CuentaBancaria;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;


@Entity
@Table(name = "vendedores")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendedor_id")
    private Long vendedorId;

    @Column(name = "vendedor_rut_pyme", nullable = false)
    private String vendedorRutPyme;

    @Column(name = "nombre_pyme", nullable = false)
    private String nombrePyme;

    @Column(name = "descripcion_pyme")
    private String descripcionPyme;

    @Column(name = "vendedor_razon_social")
    private String vendedorRazonSocial;

    @Column(name = "vendedor_fecha_registro", updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaRegistro;

    @Column(name = "vendedor_fecha_actualizacion")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaActualizacion;

    //TODO: RELACION CON LA TABLA USUARIO, traer llave foranea
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuenta_bancaria_id")
    private CuentaBancaria cuentaBancaria;

    public Vendedor(){}

    public Vendedor(Long vendedorId, String vendedorRutPyme, String nombrePyme, String descripcionPyme, String vendedorRazonSocial, LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion, Usuario usuario, CuentaBancaria cuentaBancaria) {
        this.vendedorId = vendedorId;
        this.vendedorRutPyme = vendedorRutPyme;
        this.nombrePyme = nombrePyme;
        this.descripcionPyme = descripcionPyme;
        this.vendedorRazonSocial = vendedorRazonSocial;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.usuario = usuario;
        this.cuentaBancaria = cuentaBancaria;
    }

    @PrePersist
    public void prePersist() {
        // Se establece la fecha de registro solo al crear el usuario
        if(this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        // Se actualiza la fecha de actualización al modificar el usuario
        this.fechaActualizacion = LocalDateTime.now();
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

    public String getNombrePyme() {
        return nombrePyme;
    }

    public void setNombrePyme(String nombrePyme) {
        this.nombrePyme = nombrePyme;
    }

    public String getDescripcionPyme() {
        return descripcionPyme;
    }

    public void setDescripcionPyme(String descripcionPyme) {
        this.descripcionPyme = descripcionPyme;
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

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vendedor{");
        sb.append("vendedorId=").append(vendedorId);
        sb.append(", vendedorRutPyme='").append(vendedorRutPyme).append('\'');
        sb.append(", nombrePyme='").append(nombrePyme).append('\'');
        sb.append(", descripcionPyme='").append(descripcionPyme).append('\'');
        sb.append(", vendedorRazonSocial='").append(vendedorRazonSocial).append('\'');
        sb.append(", fechaRegistro=").append(fechaRegistro);
        sb.append(", fechaActualizacion=").append(fechaActualizacion);
        sb.append(", usuario=").append(usuario);
        sb.append(", cuentaBancaria=").append(cuentaBancaria);
        sb.append('}');
        return sb.toString();
    }
}
