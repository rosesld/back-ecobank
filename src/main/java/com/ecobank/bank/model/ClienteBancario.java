package com.ecobank.bank.model;

import com.ecobank.auth.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes_bancarios")
public class ClienteBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_bancario_id")
    private Long clienteBancarioId;

    @Column(name = "rut_cliente_bancario")
    private String rutClienteBancario;

    @Column(name = "cliente_bancario_fecha_registro", updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaRegistro;

    @Column(name = "cliente_bancario_fecha_actualizacion")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaActualizacion;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaBancaria cuentaBancaria;

    public ClienteBancario() {
    }

    public ClienteBancario(Long clienteBancarioId, String rutClienteBancario, LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion, Usuario usuario, CuentaBancaria cuentaBancaria) {
        this.clienteBancarioId = clienteBancarioId;
        this.rutClienteBancario = rutClienteBancario;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.usuario = usuario;
        this.cuentaBancaria = cuentaBancaria;
    }

    @PrePersist
    public void prePersist() {
        // Se establece la fecha de registro solo al crear
        if(this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        // Se actualiza la fecha de actualización al modificar
        this.fechaActualizacion = LocalDateTime.now();
    }

    public Long getClienteBancarioId() {
        return clienteBancarioId;
    }

    public String getRutClienteBancario() {
        return rutClienteBancario;
    }

    public void setRutClienteBancario(String rutClienteBancario) {
        this.rutClienteBancario = rutClienteBancario;
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
        final StringBuilder sb = new StringBuilder("ClienteBancario{");
        sb.append("clienteBancarioId=").append(clienteBancarioId);
        sb.append(", rutClienteBancario='").append(rutClienteBancario);
        sb.append(", fechaRegistro=").append(fechaRegistro);
        sb.append(", fechaActualizacion=").append(fechaActualizacion);
        sb.append(", usuario=").append(usuario);
        sb.append(", cuentaBancaria=").append(cuentaBancaria);
        sb.append('}');
        return sb.toString();
    }
}
