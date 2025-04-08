package com.ecobank.bank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @Column(name = "estado_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estadoId;

    @Column(name = "estado_nombre", nullable = false)
    private String estadoNombre;

    public Estado() {}

    public Estado(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CuentaBancaria{");
        sb.append("estadoId=").append(estadoId);
        sb.append(", estadoNombre='").append(estadoNombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
