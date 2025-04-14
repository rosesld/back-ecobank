package com.ecobank.commerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "carrito_productos")
public class CarritoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carrito_productos_id")
    private Long carritoProductoId;

    @Column(name = "carrito_producto_cantidad", nullable = false)
    private Integer carritoCantidad;

    @Column(name = "carrito_producto_fecha_actualizacion", nullable = true)
    private LocalDateTime carritoProductoFechaActualizacion;

    // TODO: Relacion con carrito_compras
    @ManyToOne
    @JoinColumn(name = "carrito_compra_id", referencedColumnName = "carrito_compra_id", nullable = false)
    private CarritoCompra carritoCompra;

    // TODO: Relacion con producto
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id", nullable = false)
    private Producto producto;

    public CarritoProducto() {
    }

    public CarritoProducto(Long carritoProductoId, Integer carritoCantidad, LocalDateTime carritoProductoFechaActualizacion) {
        this.carritoProductoId = carritoProductoId;
        this.carritoCantidad = carritoCantidad;
        this.carritoProductoFechaActualizacion = carritoProductoFechaActualizacion;
    }

    public Long getCarritoProductoId() {
        return carritoProductoId;
    }

    public Integer getCarritoCantidad() {
        return carritoCantidad;
    }

    public void setCarritoCantidad(Integer carritoCantidad) {
        this.carritoCantidad = carritoCantidad;
    }

    public LocalDateTime getCarritoProductoFechaActualizacion() {
        return carritoProductoFechaActualizacion;
    }

    public void setCarritoProductoFechaActualizacion(LocalDateTime carritoProductoFechaActualizacion) {
        this.carritoProductoFechaActualizacion = carritoProductoFechaActualizacion;
    }

    public CarritoCompra getCarritoCompra() {
        return carritoCompra;
    }

    public void setCarritoCompra(CarritoCompra carritoCompra) {
        this.carritoCompra = carritoCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarritoProducto{");
        sb.append("carritoProductoId=").append(carritoProductoId);
        sb.append(", carritoCantidad=").append(carritoCantidad);
        sb.append(", carritoProductoFechaActualizacion=").append(carritoProductoFechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}