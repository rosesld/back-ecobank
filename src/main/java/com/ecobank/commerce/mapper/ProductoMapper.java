package com.ecobank.commerce.mapper;

import com.ecobank.commerce.dto.request.RegistroProductoDTO;
import com.ecobank.commerce.dto.response.RegistroProductoResponse;
import com.ecobank.commerce.model.Categoria;
import com.ecobank.commerce.model.Imagen;
import com.ecobank.commerce.model.Producto;
import com.ecobank.commerce.model.Vendedor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoMapper {

    public static Producto toEntity(RegistroProductoDTO dto, Vendedor vendedor, Categoria categoria) {
        Producto producto = new Producto();
        producto.setProductoNombre(dto.getNombreProducto());
        producto.setProductoDescripcion(dto.getDescripcionProducto());
        producto.setProductoPrecio(dto.getPrecioProducto());
        producto.setProductoStock(dto.getStockProducto());
        producto.setProductoDescuento(dto.getDescuentoProducto());
        producto.setProductoFechaCreacion(LocalDateTime.now());
        producto.setVendedor(vendedor);
        producto.setCategoria(categoria);
        return producto;
    }

    public static RegistroProductoResponse toDto(Producto producto, Vendedor vendedor){
        RegistroProductoResponse dto = new RegistroProductoResponse();

        dto.setNombreProducto(producto.getProductoNombre());

        dto.setDescripcionProducto(producto.getProductoDescripcion());
        dto.setPrecioProducto(producto.getProductoPrecio());
        dto.setStockProducto(producto.getProductoStock());
        dto.setDescuentoProducto(producto.getProductoDescuento());
        dto.setFechaCreacionProducto(producto.getProductoFechaCreacion());
        dto.setRazonSocialVendedor(vendedor.getVendedorRazonSocial());

        dto.setNombrePyme(vendedor.getNombrePyme());
        dto.setDescripcionPyme(vendedor.getDescripcionPyme());

        List<String> urls = producto.getImagenes().stream()
                .map(Imagen::getImagenUrl)
                .collect(Collectors.toList());
        dto.setUrlsImagenes(urls);

        return dto;
    }

}
