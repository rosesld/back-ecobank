package com.ecobank.auth.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios", indexes = @Index(name = "idx_usuario_email", columnList = "usuario_mail"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "usuario_nombre", nullable = false, length = 50)
    private String usuarioNombre;

    @Column(name = "usuario_apellido_paterno", nullable = false, length = 50)
    private String usuarioApellidoPaternno;

    @Column(name = "usuario_apellido_materno", nullable = false, length = 50)
    private String usuarioApellidoMaterno;

    @Column(name = "usuario_email", nullable = false, length = 150)
    private String usuarioEmail;

    @Column(name = "usuario_password", nullable = false, length = 254)
    private String usuarioPassword;

    @Column(name = "usuario_telefono", nullable = false, length = 12)
    private String usuarioTelefono;

    @Column(name = "usuario_fecha_registro", nullable = false, updatable = false)
    private LocalDateTime usuarioFechaRegistro;

    @Column(name = "usuario_fecha_actualizacion", nullable = true)
    private LocalDateTime usuarioFechaActualizacion;

    @ManyToMany
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long usuarioId, String usuarioNombre, String usuarioApellidoPaternno, String usuarioApellidoMaterno, String usuarioEmail, String usuarioPassword, String usuarioTelefono, LocalDateTime usuarioFechaRegistro, LocalDateTime usuarioFechaActualizacion) {
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.usuarioApellidoPaternno = usuarioApellidoPaternno;
        this.usuarioApellidoMaterno = usuarioApellidoMaterno;
        this.usuarioEmail = usuarioEmail;
        this.usuarioPassword = usuarioPassword;
        this.usuarioTelefono = usuarioTelefono;
        this.usuarioFechaRegistro = usuarioFechaRegistro;
        this.usuarioFechaActualizacion = usuarioFechaActualizacion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioApellidoPaternno() {
        return usuarioApellidoPaternno;
    }

    public void setUsuarioApellidoPaternno(String usuarioApellidoPaternno) {
        this.usuarioApellidoPaternno = usuarioApellidoPaternno;
    }

    public String getUsuarioApellidoMaterno() {
        return usuarioApellidoMaterno;
    }

    public void setUsuarioApellidoMaterno(String usuarioApellidoMaterno) {
        this.usuarioApellidoMaterno = usuarioApellidoMaterno;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioPassword() {
        return usuarioPassword;
    }

    public void setUsuarioPassword(String usuarioPassword) {
        this.usuarioPassword = usuarioPassword;
    }

    public String getUsuarioTelefono() {
        return usuarioTelefono;
    }

    public void setUsuarioTelefono(String usuarioTelefono) {
        this.usuarioTelefono = usuarioTelefono;
    }

    public LocalDateTime getUsuarioFechaRegistro() {
        return usuarioFechaRegistro;
    }

    public void setUsuarioFechaRegistro(LocalDateTime usuarioFechaRegistro) {
        this.usuarioFechaRegistro = usuarioFechaRegistro;
    }

    public LocalDateTime getUsuarioFechaActualizacion() {
        return usuarioFechaActualizacion;
    }

    public void setUsuarioFechaActualizacion(LocalDateTime usuarioFechaActualizacion) {
        this.usuarioFechaActualizacion = usuarioFechaActualizacion;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("usuarioId=").append(usuarioId);
        sb.append(", usuarioNombre='").append(usuarioNombre).append('\'');
        sb.append(", usuarioApellidoPaternno='").append(usuarioApellidoPaternno).append('\'');
        sb.append(", usuarioApellidoMaterno='").append(usuarioApellidoMaterno).append('\'');
        sb.append(", usuarioEmail='").append(usuarioEmail).append('\'');
        sb.append(", usuarioPassword='").append(usuarioPassword).append('\'');
        sb.append(", usuarioTelefono='").append(usuarioTelefono).append('\'');
        sb.append(", usuarioFechaRegistro=").append(usuarioFechaRegistro);
        sb.append(", usuarioFechaActualizacion=").append(usuarioFechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}
