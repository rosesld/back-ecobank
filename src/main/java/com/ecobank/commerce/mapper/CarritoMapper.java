package com.ecobank.commerce.mapper;

import com.ecobank.commerce.dto.response.CarritoProductoResponse;
import com.ecobank.commerce.dto.response.CarritoResponse;
import com.ecobank.commerce.model.CarritoCompra;
import com.ecobank.commerce.model.CarritoProducto;
import com.ecobank.commerce.model.Producto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarritoMapper {

    public CarritoResponse toCarritoResponse(CarritoCompra carrito, List<CarritoProducto> productos) {
        List<CarritoProductoResponse> items = productos.stream()
                .map(this::toCarritoProductoResponse)
                .collect(Collectors.toList());

        BigDecimal total = items.stream()
                .map(CarritoProductoResponse::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CarritoResponse(
                carrito.getCarritocompraId(),
                carrito.getCarritoFechaCreacion(),
                carrito.getCarritoFechaActualizacion(),
                total,
                carrito.getEstadoCarrito().getNombre(),
                items
        );
    }

    public CarritoProductoResponse toCarritoProductoResponse(CarritoProducto cp) {
        Producto p = cp.getProducto();

        BigDecimal precio = p.getProductoPrecio();
        BigDecimal descuento = p.getProductoDescuento() != null ? p.getProductoDescuento() : BigDecimal.ZERO;
        BigDecimal precioFinal = precio.subtract(precio.multiply(descuento).divide(BigDecimal.valueOf(100)));
        BigDecimal subtotal = precioFinal.multiply(BigDecimal.valueOf(cp.getCarritoCantidad()));

        return new CarritoProductoResponse(
                p.getProductoId(),
                p.getProductoNombre(),
                p.getProductoPrecio(),
                descuento,
                cp.getCarritoCantidad(),
                subtotal
        );
    }
}
