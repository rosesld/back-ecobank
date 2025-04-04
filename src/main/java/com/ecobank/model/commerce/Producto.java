package com.ecobank.model.commerce;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long productoId;

    // vendedorId

    @Column(name = "producto_nombre", nullable = false, length = 50)
    private String productoNombre;

    @Column(name = "producto_descripcion", length = 200)
    private String productoDescripcion;

    @Column(name = "producto_precio", precision = 10, scale = 2)
    private BigDecimal productoPrecio;

    @Column(name = "producto_stock", nullable = false)
    private Integer productoStock;

    @Column(name = "producto_descuento", precision = 5, scale = 2)
    private BigDecimal productoDescuento;

    @Column(name = "producto_fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime productoFechaCreacion;

    @Column(name = "producto_fecha_actualizacion", nullable = true)
    private LocalDateTime productoFechaActualizacion;

    public Producto() {
    }

    public Producto(Long productoId, String productoNombre, String productoDescripcion, BigDecimal productoPrecio, Integer productoStock, BigDecimal productoDescuento, LocalDateTime productoFechaCreacion, LocalDateTime productoFechaActualizacion) {
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.productoDescripcion = productoDescripcion;
        this.productoPrecio = productoPrecio;
        this.productoStock = productoStock;
        this.productoDescuento = productoDescuento;
        this.productoFechaCreacion = productoFechaCreacion;
        this.productoFechaActualizacion = productoFechaActualizacion;
    }

    public Long getProductoId() {
        return productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getProductoDescripcion() {
        return productoDescripcion;
    }

    public void setProductoDescripcion(String productoDescripcion) {
        this.productoDescripcion = productoDescripcion;
    }

    public BigDecimal getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(BigDecimal productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

    public Integer getProductoStock() {
        return productoStock;
    }

    public void setProductoStock(Integer productoStock) {
        this.productoStock = productoStock;
    }

    public BigDecimal getProductoDescuento() {
        return productoDescuento;
    }

    public void setProductoDescuento(BigDecimal productoDescuento) {
        this.productoDescuento = productoDescuento;
    }

    public LocalDateTime getProductoFechaCreacion() {
        return productoFechaCreacion;
    }

    public void setProductoFechaCreacion(LocalDateTime productoFechaCreacion) {
        this.productoFechaCreacion = productoFechaCreacion;
    }

    public LocalDateTime getProductoFechaActualizacion() {
        return productoFechaActualizacion;
    }

    public void setProductoFechaActualizacion(LocalDateTime productoFechaActualizacion) {
        this.productoFechaActualizacion = productoFechaActualizacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Producto{");
        sb.append("productoId=").append(productoId);
        sb.append(", productoNombre='").append(productoNombre).append('\'');
        sb.append(", productoDescripcion='").append(productoDescripcion).append('\'');
        sb.append(", productoPrecio=").append(productoPrecio);
        sb.append(", productoStock=").append(productoStock);
        sb.append(", productoDescuento=").append(productoDescuento);
        sb.append(", productoFechaCreacion=").append(productoFechaCreacion);
        sb.append(", productoFechaActualizacion=").append(productoFechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}
