package com.ecobank.model.commerce;

import com.ecobank.model.auth.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "pedido_fecha", updatable = false)
    private LocalDateTime pedidoFecha;

    @Column(name = "pedido_fecha_actulizacion")
    private LocalDateTime pedidoFechaActualizacion;

    //TODO: RELACION CON LA TABLA USUARIO, traer llave foranea
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    //TODO: RELACION CON LA TABLA DIRECCION ENVIO, traer llave foranea
    @ManyToOne
    @JoinColumn(name = "direccion_envio_id", referencedColumnName = "direccion_envio_id", nullable = false)
    private DireccionEnvio direccionEnvio;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DireccionEnvio getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(DireccionEnvio direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
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
