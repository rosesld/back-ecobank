package com.ecobank.commerce.service.impl;

import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.repository.UsuarioRepository;
import com.ecobank.commerce.dto.response.CarritoProductoResponse;
import com.ecobank.commerce.dto.response.CarritoResponse;
import com.ecobank.commerce.dto.response.CompraResponse;
import com.ecobank.commerce.mapper.CarritoMapper;
import com.ecobank.commerce.mapper.CompraMapper;
import com.ecobank.commerce.model.*;
import com.ecobank.commerce.repository.*;
import com.ecobank.commerce.service.services.ConfirmarCompraService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConfirmarCompraServiceImpl implements ConfirmarCompraService {

    private final UsuarioRepository usuarioRepository;
    private final EstadoCarritoRepository estadoCarritoRepository;
    private final CarritoCompraRepository carritoCompraRepository;
    private final CarritoProductoRepository carritoProductoRepository;
    private final DireccionEnvioRepository direccionEnvioRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final CarritoMapper carritoMapper;
    private final CompraMapper compraMapper;

    private static final String ESTADO_ABIERTO = "ABIERTO";
    private static final String ESTADO_CERRADO = "CERRADO";
    private static final String ESTADO_PEDIDO_PENDIENTE = "PENDIENTE";



    public ConfirmarCompraServiceImpl(UsuarioRepository usuarioRepository, EstadoCarritoRepository estadoCarritoRepository, CarritoCompraRepository carritoCompraRepository, CarritoProductoRepository carritoProductoRepository, DireccionEnvioRepository direccionEnvioRepository, EstadoPedidoRepository estadoPedidoRepository, ProductoRepository productoRepository, PedidoRepository pedidoRepository, DetallePedidoRepository detallePedidoRepository, CarritoMapper carritoMapper, CompraMapper compraMapper) {
        this.usuarioRepository = usuarioRepository;
        this.estadoCarritoRepository = estadoCarritoRepository;
        this.carritoCompraRepository = carritoCompraRepository;
        this.carritoProductoRepository = carritoProductoRepository;
        this.direccionEnvioRepository = direccionEnvioRepository;
        this.estadoPedidoRepository = estadoPedidoRepository;
        this.productoRepository = productoRepository;
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.carritoMapper = carritoMapper;
        this.compraMapper = compraMapper;
    }

    @Override
    @Transactional
    public CompraResponse confirmarCompra(Long usuarioId, Long direccionEnvioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EstadoCarrito estadoAbierto = estadoCarritoRepository.findByNombre(ESTADO_ABIERTO)
                .orElseThrow(() -> new RuntimeException("Estado abierto no encontrado"));

        CarritoCompra carrito = carritoCompraRepository.findByUsuarioAndEstadoCarrito(usuario, estadoAbierto)
                .orElseThrow(() -> new RuntimeException("Carrito activo no encontrado"));

        List<CarritoProducto> productosEnCarrito = carritoProductoRepository.findByCarritoCompra(carrito);
        if (productosEnCarrito.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        DireccionEnvio direccionEnvio = direccionEnvioRepository.findById(direccionEnvioId)
                .orElseThrow(() -> new RuntimeException("Dirección de envío no encontrada"));

        EstadoPedido estadoPedidoPendiente = estadoPedidoRepository.findByNombre(ESTADO_PEDIDO_PENDIENTE)
                .orElseThrow(() -> new RuntimeException("Estado de pedido 'PENDIENTE' no encontrado"));

        BigDecimal totalPedido = BigDecimal.ZERO;

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setDireccionEnvio(direccionEnvio);
        pedido.setEstadoPedido(estadoPedidoPendiente);
        pedido.setPedidoFecha(LocalDateTime.now());
        pedido.setPedidoFechaActualizacion(LocalDateTime.now());

        for (CarritoProducto carritoProducto : productosEnCarrito) {
            Producto producto = carritoProducto.getProducto();

            if (producto.getProductoStock() < carritoProducto.getCarritoCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getProductoNombre());
            }

            producto.setProductoStock(producto.getProductoStock() - carritoProducto.getCarritoCantidad());
            productoRepository.save(producto);

            BigDecimal subTotal = producto.getProductoPrecio().multiply(BigDecimal.valueOf(carritoProducto.getCarritoCantidad()));
            totalPedido = totalPedido.add(subTotal);

            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setPedido(pedido);
            detallePedido.setProducto(producto);
            detallePedido.setDetallePedidoCantidad(carritoProducto.getCarritoCantidad());
            detallePedido.setDetallePedidoPrecio(producto.getProductoPrecio());
            detallePedido.setEstadoPedido(estadoPedidoPendiente);

            pedido.getDetalles().add(detallePedido);
        }

        pedido.setPedidoTotal(totalPedido);

        pedidoRepository.save(pedido);
        for (DetallePedido detalle : pedido.getDetalles()) {
            detallePedidoRepository.save(detalle);
        }

        EstadoCarrito estadoCarritoCerrado = estadoCarritoRepository.findByNombre(ESTADO_CERRADO)
                .orElseThrow(() -> new RuntimeException("Estado cerrado no encontrado"));

        carrito.setEstadoCarrito(estadoCarritoCerrado);
        carrito.setCarritoFechaActualizacion(LocalDateTime.now());
        carritoCompraRepository.save(carrito);

        carritoProductoRepository.deleteAll(productosEnCarrito);

        List<CarritoProductoResponse> productosResponse = pedido.getDetalles().stream()
                .map(detalle -> new CarritoProductoResponse(
                        detalle.getProducto().getProductoId(),
                        detalle.getProducto().getProductoNombre(),
                        detalle.getDetallePedidoPrecio(),
                        BigDecimal.ZERO,
                        detalle.getDetallePedidoCantidad(),
                        detalle.getDetallePedidoPrecio().multiply(BigDecimal.valueOf(detalle.getDetallePedidoCantidad()))
                ))
                .toList();

        return compraMapper.toCompraResponse(pedido, productosResponse, carrito);
    }
}
