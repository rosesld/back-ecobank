package com.ecobank.commerce.dto;

import com.ecobank.commerce.model.Categoria;
import com.ecobank.commerce.model.Vendedor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RegistroProductoDTO {

    private String nombreProducto;
    private String descripcionProducto;
    private BigDecimal precioProducto;
    private Integer stockProducto;
    private BigDecimal descuentoProducto;
    private LocalDateTime fechaCreacionProducto;

    private Vendedor vendedor;

    private Categoria categoria;

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(Integer stockProducto) {
        this.stockProducto = stockProducto;
    }

    public BigDecimal getDescuentoProducto() {
        return descuentoProducto;
    }

    public void setDescuentoProducto(BigDecimal descuentoProducto) {
        this.descuentoProducto = descuentoProducto;
    }

    public LocalDateTime getFechaCreacionProducto() {
        return fechaCreacionProducto;
    }

    public void setFechaCreacionProducto(LocalDateTime fechaCreacionProducto) {
        this.fechaCreacionProducto = fechaCreacionProducto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
