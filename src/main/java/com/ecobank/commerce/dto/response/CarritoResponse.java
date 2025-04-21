package com.ecobank.commerce.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CarritoResponse {
    private Integer productoId;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String estado;
    private BigDecimal total;
    private List<CarritoProductoResponse> productos;

    public CarritoResponse() {}

    public CarritoResponse(Integer productoId, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, BigDecimal total, String estado, List<CarritoProductoResponse> productos) {
        this.productoId = productoId;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.total = total;
        this.estado = estado;
        this.productos = productos;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<CarritoProductoResponse> getProductos() {
        return productos;
    }

    public void setProductos(List<CarritoProductoResponse> productos) {
        this.productos = productos;
    }
}
