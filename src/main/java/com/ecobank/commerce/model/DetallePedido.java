package com.ecobank.commerce.model;

import com.ecobank.bank.model.Estado;
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
    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "pedido_id", nullable = false)
    private Pedido pedido;

    // TODO: Relacion con Producto
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id", nullable = false)
    private Producto producto;

    // TODO: Relacion con Estado
    @ManyToOne
    @JoinColumn(name = "estado_pedido_id", referencedColumnName = "estado_pedido_id", nullable = false)
    private EstadoPedido estadoPedido;

    public DetallePedido(){}

    public DetallePedido(Integer detallePedidoCantidad, LocalDateTime detallePedidoFechaActualizacion, Producto producto, Pedido pedido, EstadoPedido estadoPedido, BigDecimal detallePedidoPrecio, Long detallePedidoId) {
        this.detallePedidoCantidad = detallePedidoCantidad;
        this.detallePedidoFechaActualizacion = detallePedidoFechaActualizacion;
        this.producto = producto;
        this.pedido = pedido;
        this.estadoPedido = estadoPedido;
        this.detallePedidoPrecio = detallePedidoPrecio;
        this.detallePedidoId = detallePedidoId;
    }

    public Long getDetallePedidoId() {
        return detallePedidoId;
    }

    public void setDetallePedidoId(Long detallePedidoId) {
        this.detallePedidoId = detallePedidoId;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DetallePedido{");
        sb.append("detallePedidoId=").append(detallePedidoId);
        sb.append(", detallePedidoCantidad=").append(detallePedidoCantidad);
        sb.append(", detallePedidoPrecio=").append(detallePedidoPrecio);
        sb.append(", detallePedidoFechaActualizacion=").append(detallePedidoFechaActualizacion);
        sb.append(", pedido=").append(pedido);
        sb.append(", producto=").append(producto);
        sb.append(", estadoPedido=").append(estadoPedido);
        sb.append('}');
        return sb.toString();
    }
}
