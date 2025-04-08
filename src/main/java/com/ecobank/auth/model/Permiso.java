package com.ecobank.auth.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="permisos")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permiso_id")
    private Long permisoId;

    @Column(name = "permiso_nombre", nullable = false, length = 100, unique = true)
    private String permisoNombre;

    @Column(name = "permiso_descripcion", nullable = false, length = 254)
    private String permisoDescripcion;

    @Column(name = "permiso_fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime permisoFechaCreacion;

    @Column(name = "permiso_fecha_actualizacion", nullable = true)
    private LocalDateTime permisoFechaActualizacion;

    public Permiso() {
    }

    public Permiso(Long permisoId, String permisoNombre, String permisoDescripcion, LocalDateTime permisoFechaCreacion, LocalDateTime permisoFechaActualizacion) {
        this.permisoId = permisoId;
        this.permisoNombre = permisoNombre;
        this.permisoDescripcion = permisoDescripcion;
        this.permisoFechaCreacion = permisoFechaCreacion;
        this.permisoFechaActualizacion = permisoFechaActualizacion;
    }

    public Long getPermisoId() {
        return permisoId;
    }

    public void setPermisoNombre(String permisoNombre) {
        this.permisoNombre = permisoNombre;
    }

    public String getPermisoDescripcion() {
        return permisoDescripcion;
    }

    public void setPermisoDescripcion(String permisoDescripcion) {
        this.permisoDescripcion = permisoDescripcion;
    }

    public LocalDateTime getPermisoFechaCreacion() {
        return permisoFechaCreacion;
    }

    public void setPermisoFechaCreacion(LocalDateTime permisoFechaCreacion) {
        this.permisoFechaCreacion = permisoFechaCreacion;
    }

    public LocalDateTime getPermisoFechaActualizacion() {
        return permisoFechaActualizacion;
    }

    public void setPermisoFechaActualizacion(LocalDateTime permisoFechaActualizacion) {
        this.permisoFechaActualizacion = permisoFechaActualizacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Permiso{");
        sb.append("permisoId=").append(permisoId);
        sb.append(", permisoNombre='").append(permisoNombre).append('\'');
        sb.append(", permisoDescripcion='").append(permisoDescripcion).append('\'');
        sb.append(", permisoFechaCreacion=").append(permisoFechaCreacion);
        sb.append(", permisoFechaActualizacion=").append(permisoFechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}
