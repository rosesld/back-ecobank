package com.ecobank.model.commerce;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;

    @Column(name = "pedido_fecha", updatable = false)
    private LocalDateTime pedidoFecha;

    @Column(name = "pedido_fecha_actulizacion")
    private LocalDateTime pedidoFechaActualizacion;

    //TODO: RELACION CON LA TABLA USUARIO, traer llave foranea
    //TODO: RELACION CON LA TABLA DIRECCION ENVIO, traer llave foranea

    public Pedido (){}

    public Pedido(Long pedidoId, LocalDateTime pedidoFecha, LocalDateTime pedidoFechaActualizacion) {
        this.pedidoId = pedidoId;
        this.pedidoFecha = pedidoFecha;
        this.pedidoFechaActualizacion = pedidoFechaActualizacion;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public LocalDateTime getPedidoFecha() {
        return pedidoFecha;
    }

    public void setPedidoFecha(LocalDateTime pedidoFecha) {
        this.pedidoFecha = pedidoFecha;
    }

    public LocalDateTime getPedidoFechaActualizacion() {
        return pedidoFechaActualizacion;
    }

    public void setPedidoFechaActualizacion(LocalDateTime pedidoFechaActualizacion) {
        this.pedidoFechaActualizacion = pedidoFechaActualizacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pedido{");
        sb.append("pedidoId=").append(pedidoId);
        sb.append(", pedidoFecha=").append(pedidoFecha);
        sb.append(", pedidoFechaActualizacion=").append(pedidoFechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}
