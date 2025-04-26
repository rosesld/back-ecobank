package com.ecobank.commerce.service.services;

import com.ecobank.commerce.dto.response.CarritoResponse;

public interface CarritoCompraService {
    CarritoResponse obtenerCarritoActivo(Long usuarioId);
    CarritoResponse agregarProducto(Long usuarioId, Long productoId, Integer cantidad);
    CarritoResponse eliminarProducto(Long usuarioId, Long productoId);
    CarritoResponse actualizarCantidadProducto(Long usuarioId, Long productoId, Integer cantidad);
    CarritoResponse vaciarCarrito(Long usuarioId);
}
