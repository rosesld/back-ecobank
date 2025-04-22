package com.ecobank.commerce.model;

import com.ecobank.auth.model.Usuario;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "pedido_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal pedidoTotal;


    @Column(name = "pedido_fecha", updatable = false)
    private LocalDateTime pedidoFecha;

    @Column(name = "pedido_fecha_actualizacion")
    private LocalDateTime pedidoFechaActualizacion;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    //TODO: RELACION CON LA TABLA USUARIO, traer llave foranea
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    //TODO: RELACION CON LA TABLA DIRECCION ENVIO, traer llave foranea
    @ManyToOne
    @JoinColumn(name = "direccion_envio_id", referencedColumnName = "direccion_envio_id", nullable = false)
    private DireccionEnvio direccionEnvio;

    @ManyToOne
    @JoinColumn(name = "estado_pedido_id", referencedColumnName = "estado_pedido_id", nullable = false)
    private EstadoPedido estadoPedido;

    public Pedido (){}

    public Pedido(Long pedidoId, BigDecimal pedidoTotal, LocalDateTime pedidoFecha, List<DetallePedido> detalles, EstadoPedido estadoPedido, DireccionEnvio direccionEnvio, Usuario usuario, LocalDateTime pedidoFechaActualizacion) {
        this.pedidoId = pedidoId;
        this.pedidoTotal = pedidoTotal;
        this.pedidoFecha = pedidoFecha;
        this.detalles = detalles;
        this.estadoPedido = estadoPedido;
        this.direccionEnvio = direccionEnvio;
        this.usuario = usuario;
        this.pedidoFechaActualizacion = pedidoFechaActualizacion;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
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

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public BigDecimal getPedidoTotal() {
        return pedidoTotal;
    }

    public void setPedidoTotal(BigDecimal pedidoTotal) {
        this.pedidoTotal = pedidoTotal;
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
