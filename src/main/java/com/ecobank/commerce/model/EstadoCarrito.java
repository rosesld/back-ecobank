package com.ecobank.commerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_carrito")
public class EstadoCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estado_carrito_id")
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    public EstadoCarrito() {}

    public EstadoCarrito(String nombre) {
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
