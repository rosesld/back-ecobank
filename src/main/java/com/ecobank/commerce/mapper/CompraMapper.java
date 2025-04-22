package com.ecobank.commerce.mapper;

import com.ecobank.commerce.dto.response.CarritoProductoResponse;
import com.ecobank.commerce.dto.response.CompraResponse;
import com.ecobank.commerce.dto.response.DireccionEnvioResponse;
import com.ecobank.commerce.model.CarritoCompra;
import com.ecobank.commerce.model.DireccionEnvio;
import com.ecobank.commerce.model.Pedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CompraMapper {

    public CompraResponse toCompraResponse(Pedido pedido, List<CarritoProductoResponse> productos, CarritoCompra carrito) {
        BigDecimal total = productos.stream()
                .map(CarritoProductoResponse::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String estadoCarrito = carrito.getEstadoCarrito() != null
                ? carrito.getEstadoCarrito().getNombre()
                : "Desconocido";

        DireccionEnvioResponse direccionEnvioResponse = mapDireccionEnvio(pedido.getDireccionEnvio());

        return new CompraResponse(
                pedido.getPedidoId(),
                pedido.getPedidoFecha(),
                pedido.getPedidoFechaActualizacion(),
                pedido.getEstadoPedido().getNombre(),
                total,
                productos,
                estadoCarrito,
                direccionEnvioResponse
        );
    }

    private DireccionEnvioResponse mapDireccionEnvio(DireccionEnvio direccionEnvio) {
        return new DireccionEnvioResponse(
                direccionEnvio.getDireccionenvioId(),
                direccionEnvio.getCalle(),
                direccionEnvio.getNumero(),
                direccionEnvio.getNota(),
                direccionEnvio.getRegion() != null ? direccionEnvio.getRegion().getRegionNombre() : null,
                direccionEnvio.getComuna() != null ? direccionEnvio.getComuna().getComunaNombre() : null
        );
    }
}
