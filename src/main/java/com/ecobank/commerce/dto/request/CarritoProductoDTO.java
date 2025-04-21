package com.ecobank.commerce.dto.request;

import java.math.BigDecimal;

public class CarritoProductoDTO {

    private Long productoId;
    private String productoNombre;
    private BigDecimal productoPrecio;
    private BigDecimal productoDescuento;
    private Integer cantidad;
    private BigDecimal subtotal;

    public CarritoProductoDTO() {}

    public CarritoProductoDTO(Long productoId, String productoNombre, BigDecimal productoPrecio, BigDecimal productoDescuento, Integer cantidad, BigDecimal subtotal) {
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.productoPrecio = productoPrecio;
        this.productoDescuento = productoDescuento;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public BigDecimal getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(BigDecimal productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getProductoDescuento() {
        return productoDescuento;
    }

    public void setProductoDescuento(BigDecimal productoDescuento) {
        this.productoDescuento = productoDescuento;
    }
}
