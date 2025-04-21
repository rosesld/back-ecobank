package com.ecobank.commerce.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CarritoDTO {

    private Integer carritoId;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String estado;
    private BigDecimal total;
    private List<CarritoProductoDTO> productos;

    public CarritoDTO() {}

    public CarritoDTO(Integer carritoId, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, String estado, BigDecimal total, List<CarritoProductoDTO> productos) {
        this.carritoId = carritoId;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
        this.total = total;
        this.productos = productos;
    }

    public Integer getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Integer carritoId) {
        this.carritoId = carritoId;
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

    public List<CarritoProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<CarritoProductoDTO> productos) {
        this.productos = productos;
    }
}
