package com.ecobank.commerce.dto.response;

import java.math.BigDecimal;

public class CarritoProductoResponse {

    private Long productoId;
    private String nombre;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;
    private Integer cantidad;
    private BigDecimal subTotal;

    public CarritoProductoResponse() {}

    public CarritoProductoResponse(Long productoId, String nombre, BigDecimal precioUnitario, BigDecimal descuento, Integer cantidad, BigDecimal subTotal) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
