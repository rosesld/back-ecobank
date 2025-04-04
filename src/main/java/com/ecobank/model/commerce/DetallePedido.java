package com.ecobank.model.commerce;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalles_pedidos")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalle_pedido_id")
    private Long detallePedidoId;

    @Column(name = "detalle_pedido_cantidad", nullable = false)
    private Integer detallePedidoCantidad;

    @Column(name = "detalle_pedido_precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal detallePedidoPrecio;

    @Column(name = "detalles_pedido_fecha_actualizacion", nullable = true)
    private LocalDateTime detallePedidoFechaActualizacion;

    // TODO: Relacion con Pedido
    // TODO: Relacion con Producto
    // TODO: Relacion con Estado

    public DetallePedido(){}

    public DetallePedido(Long detallePedidoId, Integer detallePedidoCantidad, BigDecimal detallePedidoPrecio, LocalDateTime detallePedidoFechaActualizacion) {
        this.detallePedidoId = detallePedidoId;
        this.detallePedidoCantidad = detallePedidoCantidad;
        this.detallePedidoPrecio = detallePedidoPrecio;
        this.detallePedidoFechaActualizacion = detallePedidoFechaActualizacion;
    }

    public Long getDetallePedidoId() {
        return detallePedidoId;
    }

    public Integer getDetallePedidoCantidad() {
        return detallePedidoCantidad;
    }

    public void setDetallePedidoCantidad(Integer detallePedidoCantidad) {
        this.detallePedidoCantidad = detallePedidoCantidad;
    }

    public BigDecimal getDetallePedidoPrecio() {
        return detallePedidoPrecio;
    }

    public void setDetallePedidoPrecio(BigDecimal detallePedidoPrecio) {
        this.detallePedidoPrecio = detallePedidoPrecio;
    }

    public LocalDateTime getDetallePedidoFechaActualizacion() {
        return detallePedidoFechaActualizacion;
    }

    public void setDetallePedidoFechaActualizacion(LocalDateTime detallePedidoFechaActualizacion) {
        this.detallePedidoFechaActualizacion = detallePedidoFechaActualizacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DetallePedido{");
        sb.append("detallePedidoId=").append(detallePedidoId);
        sb.append(", detallePedidoCantidad=").append(detallePedidoCantidad);
        sb.append(", detallePedidoPrecio=").append(detallePedidoPrecio);
        sb.append(", detallePedidoFechaActualizacion=").append(detallePedidoFechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}
