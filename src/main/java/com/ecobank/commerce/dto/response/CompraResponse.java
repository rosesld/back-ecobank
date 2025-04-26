package com.ecobank.commerce.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CompraResponse {

    private Long pedidoId;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String estado;
    private BigDecimal total;
    private List<CarritoProductoResponse> productos;

    // Campos nuevos
    private String estadoCarrito;
    private DireccionEnvioResponse direccionEnvio;

    public CompraResponse() {
    }

    public CompraResponse(Long pedidoId, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, String estado, BigDecimal total, List<CarritoProductoResponse> productos, String estadoCarrito, DireccionEnvioResponse direccionEnvio) {
        this.pedidoId = pedidoId;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
        this.total = total;
        this.productos = productos;
        this.estadoCarrito = estadoCarrito;
        this.direccionEnvio = direccionEnvio;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<CarritoProductoResponse> getProductos() {
        return productos;
    }

    public void setProductos(List<CarritoProductoResponse> productos) {
        this.productos = productos;
    }

    public String getEstadoCarrito() {
        return estadoCarrito;
    }

    public void setEstadoCarrito(String estadoCarrito) {
        this.estadoCarrito = estadoCarrito;
    }

    public DireccionEnvioResponse getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(DireccionEnvioResponse direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}
