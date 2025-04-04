package com.ecobank.model.commerce;

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
    // TODO: Relacion con producto


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