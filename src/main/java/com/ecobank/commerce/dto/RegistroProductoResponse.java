package com.ecobank.commerce.dto;

import com.ecobank.commerce.model.Imagen;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RegistroProductoResponse {

    private Long productoId;
    private String nombreProducto;
    private String descripcionProducto;
    private BigDecimal precioProducto;
    private Integer stockPorducto;
    private BigDecimal descuentoProducto;
    private LocalDateTime fechaCreacionProducto;

    private List<String> urlsImagenes;

    private String razonSocialVendedor;

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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

    public Integer getStockPorducto() {
        return stockPorducto;
    }

    public void setStockPorducto(Integer stockPorducto) {
        this.stockPorducto = stockPorducto;
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

    public List<String> getUrlsImagenes() {
        return urlsImagenes;
    }

    public void setUrlsImagenes(List<String> urlsImagenes) {
        this.urlsImagenes = urlsImagenes;
    }

    public String getRazonSocialVendedor() {
        return razonSocialVendedor;
    }

    public void setRazonSocialVendedor(String razonSocialVendedor) {
        this.razonSocialVendedor = razonSocialVendedor;
    }
}
