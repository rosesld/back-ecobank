package com.ecobank.commerce.service.impl;

import com.ecobank.commerce.dto.response.CompraHistorialResponse;
import com.ecobank.commerce.dto.response.DetallePedidoResponse;
import com.ecobank.commerce.model.DetallePedido;
import com.ecobank.commerce.model.Pedido;
import com.ecobank.commerce.repository.DetallePedidoRepository;
import com.ecobank.commerce.repository.PedidoRepository;
import com.ecobank.commerce.service.services.HistorialCompraService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialCompraServiceImpl implements HistorialCompraService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    public HistorialCompraServiceImpl(PedidoRepository pedidoRepository, DetallePedidoRepository detallePedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @Override
    public List<CompraHistorialResponse> obtenerHistorialCompras(Long usuarioId) {
        // Obtener todos los pedidos del usuario
        List<Pedido> pedidos = pedidoRepository.findByUsuario_UsuarioId(usuarioId);

        // Mapear los pedidos a la respuesta esperada
        return pedidos.stream()
                .map(pedido -> {
                    CompraHistorialResponse response = new CompraHistorialResponse();
                    response.setPedidoId(pedido.getPedidoId());
                    response.setFechaPedido(pedido.getPedidoFecha());
                    response.setEstadoPedido(pedido.getEstadoPedido().getNombre());
                    response.setTotalPedido(pedido.getPedidoTotal());

                    // Mapear los detalles de cada pedido
                    List<DetallePedidoResponse> detalles = pedido.getDetalles().stream()
                            .map(this::mapearDetallePedido)
                            .collect(Collectors.toList());

                    response.setDetalles(detalles);
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Método auxiliar para mapear los detalles del pedido
    private DetallePedidoResponse mapearDetallePedido(DetallePedido detallePedido) {
        DetallePedidoResponse detalleResponse = new DetallePedidoResponse();
        detalleResponse.setProductoId(detallePedido.getProducto().getProductoId());
        detalleResponse.setProductoNombre(detallePedido.getProducto().getProductoNombre());
        detalleResponse.setCantidad(detallePedido.getDetallePedidoCantidad());
        detalleResponse.setPrecio(detallePedido.getDetallePedidoPrecio());
        return detalleResponse;
    }
}

