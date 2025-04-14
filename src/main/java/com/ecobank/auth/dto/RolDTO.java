package com.ecobank.auth.dto;

import java.time.LocalDateTime;

public class RolDTO {
    private Long rolId;
    private String rolNombre;
    private LocalDateTime rolFechaCreacion;
    private LocalDateTime rolFechaActualizacion;

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
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
}
