package com.ecobank.model.auth;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "rol_nombre", nullable = false, length = 50)
    private String rolNombre;

    @Column(name = "rol_fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime rolFechaCreacion;

    @Column(name = "rol_fecha_actualizacion", nullable = true)
    private LocalDateTime rolFechaActualizacion;

    @ManyToMany
    @JoinTable(
            name = "roles_permisos",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id")
    )
    private Set<Permiso> permisos = new HashSet<>();

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new HashSet<>();

    public Rol() {
    }

    public Rol(Long rolId, String rolNombre, LocalDateTime rolFechaCreacion, LocalDateTime rolFechaActualizacion) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.rolFechaCreacion = rolFechaCreacion;
        this.rolFechaActualizacion = rolFechaActualizacion;
    }

    public Long getRolId() {
        return rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public LocalDateTime getRolFechaCreacion() {
        return rolFechaCreacion;
    }

    public void setRolFechaCreacion(LocalDateTime rolFechaCreacion) {
        this.rolFechaCreacion = rolFechaCreacion;
    }

    public LocalDateTime getRolFechaActualizacion() {
        return rolFechaActualizacion;
    }

    public void setRolFechaActualizacion(LocalDateTime rolFechaActualizacion) {
        this.rolFechaActualizacion = rolFechaActualizacion;
    }

    public Set<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(Set<Permiso> permisos) {
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rol{");
        sb.append("rolId=").append(rolId);
        sb.append(", rolNombre='").append(rolNombre).append('\'');
        sb.append(", rolFechaCreacion=").append(rolFechaCreacion);
        sb.append(", rolFechaActualizacion=").append(rolFechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}
