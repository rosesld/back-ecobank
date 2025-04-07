package com.ecobank.model.commerce;


import com.ecobank.model.auth.Usuario;
import com.ecobank.model.bank.Estado;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "Carrito_Compra")
public class CarritoCompra {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "carrito_compra_id")
        private Integer carritocompraId;

        @Column(name = "usuario_id", nullable = false)
        private Integer usuarioId;

        @Column(name = "carrito_estado", nullable = false)
        private Integer carritoEstado;

        @Column(name = "carrito_fecha_creacion", nullable = false)
        private LocalDateTime carritoFechaCreacion;

        @Column(name = "carrito_fecha_actualizacion", nullable = false, updatable = true)
        private LocalDateTime carritoFechaActualizacion;

        @ManyToOne
        @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
        private Usuario usuario;

        @ManyToOne
        @JoinColumn(name = "carrito_estado", referencedColumnName = "estado_id", nullable = false)
        private Estado estado;

        public CarritoCompra() {}

        public CarritoCompra(Integer carritocompraId, Integer usuarioId, Integer carritoEstado, LocalDateTime carritoFechaCreacion, LocalDateTime carritoFechaActualizacion) {
        this.carritocompraId = carritocompraId;
        this.usuarioId = usuarioId;
        this.carritoEstado = carritoEstado;
        this.carritoFechaCreacion = carritoFechaCreacion;
        this.carritoFechaActualizacion = carritoFechaActualizacion;
    }

    public Integer getCarritocompraId() {
        return carritocompraId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getCarritoEstado() {
        return carritoEstado;
    }

    public void setCarritoEstado(Integer carritoEstado) {
        this.carritoEstado = carritoEstado;
    }

    public LocalDateTime getCarritoFechaCreacion() {
        return carritoFechaCreacion;
    }

    public void setCarritoFechaCreacion(LocalDateTime carritoFechaCreacion) {
        this.carritoFechaCreacion = carritoFechaCreacion;
    }

    public LocalDateTime getCarritoFechaActualizacion() {
        return carritoFechaActualizacion;
    }

    public void setCarritoFechaActualizacion(LocalDateTime carritoFechaActualizacion) {
        this.carritoFechaActualizacion = carritoFechaActualizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarritoCompra{");
        sb.append("carritocompraId=").append(carritocompraId);
        sb.append(", usuarioId=").append(usuarioId);
        sb.append(", carritoEstado=").append(carritoEstado);
        sb.append(", carritoFechaCreacion=").append(carritoFechaCreacion);
        sb.append(", carritoFechaActualizacion=").append(carritoFechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}

