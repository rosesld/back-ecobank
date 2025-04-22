package com.ecobank.commerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_pedido")
public class EstadoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estado_pedido_id")
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    public EstadoPedido() {}

    public EstadoPedido(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
