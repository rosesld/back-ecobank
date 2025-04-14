package com.ecobank.commerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "imagenes")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagen_id")
    private Long imagenId;

    @Column(name = "imagen_url", nullable = false)
    private String imagenUrl;

    @Column(name = "imagen_fecha", nullable = false, updatable = true)
    private LocalDateTime imagenFecha;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id", nullable = false)
    private Producto producto;

    public Imagen() {}


    public Imagen(Long imagenId, String imagenUrl, LocalDateTime imagenFecha) {
        this.imagenId = imagenId;
        this.imagenUrl = imagenUrl;
        this.imagenFecha = imagenFecha;
    }

    public Long getImagenId() {
        return imagenId;
    }

    public void setImagenId(Long imagenId) {
        this.imagenId = imagenId;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public LocalDateTime getImagenFecha() {
        return imagenFecha;
    }

    public void setImagenFecha(LocalDateTime imagenFecha) {
        this.imagenFecha = imagenFecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // TODO: Averigurar esto.
    @PreUpdate
    public void actualizarFecha() {
        this.imagenFecha = LocalDateTime.now();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Imagen{");
        sb.append("imagenId=").append(imagenId);
        sb.append(", imagenUrl='").append(imagenUrl).append('\'');
        sb.append(", imagenFecha=").append(imagenFecha);
        sb.append('}');
        return sb.toString();
    }
}
