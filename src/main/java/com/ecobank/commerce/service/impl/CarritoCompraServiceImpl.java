package com.ecobank.commerce.service.impl;

import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.repository.UsuarioRepository;
import com.ecobank.bank.repository.EstadoRepository;
import com.ecobank.commerce.dto.response.CarritoResponse;
import com.ecobank.commerce.mapper.CarritoMapper;
import com.ecobank.commerce.model.CarritoCompra;
import com.ecobank.commerce.model.CarritoProducto;
import com.ecobank.commerce.model.EstadoCarrito;
import com.ecobank.commerce.model.Producto;
import com.ecobank.commerce.repository.CarritoCompraRepository;
import com.ecobank.commerce.repository.CarritoProductoRepository;
import com.ecobank.commerce.repository.EstadoCarritoRepository;
import com.ecobank.commerce.repository.ProductoRepository;
import com.ecobank.commerce.service.services.CarritoCompraService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraService {

    private final CarritoCompraRepository carritoCompraRepository;
    private final UsuarioRepository usuarioRepository;
    private final CarritoMapper carritoMapper;
    private final CarritoProductoRepository carritoProductoRepository;
    private final ProductoRepository productoRepository;
    private static final String ESTADO_ABIERTO = "ABIERTO";
    private final EstadoCarritoRepository estadoCarritoRepository;


    public CarritoCompraServiceImpl(CarritoCompraRepository carritoCompraRepository, UsuarioRepository usuarioRepository, CarritoMapper carritoMapper, CarritoProductoRepository carritoProductoRepository, ProductoRepository productoRepository, EstadoCarritoRepository estadoCarritoRepository) {
        this.carritoCompraRepository = carritoCompraRepository;
        this.usuarioRepository = usuarioRepository;
        this.carritoMapper = carritoMapper;
        this.carritoProductoRepository = carritoProductoRepository;
        this.productoRepository = productoRepository;
        this.estadoCarritoRepository = estadoCarritoRepository;
    }


    @Override
    public CarritoResponse obtenerCarritoActivo(Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EstadoCarrito estadoAbierto = estadoCarritoRepository.findByNombre(ESTADO_ABIERTO)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));


        Optional<CarritoCompra> optionalCarrito = carritoCompraRepository
                .findByUsuarioAndEstadoCarrito(usuario, estadoAbierto);


        CarritoCompra carrito = optionalCarrito.orElseGet(() -> {
            CarritoCompra nuevo = new CarritoCompra();
            nuevo.setUsuario(usuario);
            nuevo.setEstadoCarrito(estadoAbierto);
            nuevo.setCarritoFechaCreacion(LocalDateTime.now());
            nuevo.setCarritoFechaActualizacion(LocalDateTime.now());
            return carritoCompraRepository.save(nuevo);
        });

        List<CarritoProducto> productos = carritoProductoRepository.findByCarritoCompra(carrito);


        return carritoMapper.toCarritoResponse(carrito, productos);
    }

    @Override
    @Transactional
    public CarritoResponse agregarProducto(Long usuarioId, Long productoId, Integer cantidad) {

        CarritoCompra carrito = carritoCompraRepository
                .findByUsuarioAndEstadoCarrito(
                        usuarioRepository.findById(usuarioId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")),
                        estadoCarritoRepository.findByNombre(ESTADO_ABIERTO)
                                .orElseThrow(() -> new RuntimeException("Estado 'ABIERTO' no encontrado"))
                ).orElseThrow();

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getProductoStock() < cantidad) {
            throw new RuntimeException("No hay stock suficiente para el producto " + producto.getProductoNombre());
        }

        CarritoProducto carritoProducto = carritoProductoRepository
                .findByCarritoCompraAndProducto(carrito, producto)
                .orElseGet(() -> {
                    CarritoProducto nuevo = new CarritoProducto();
                    nuevo.setCarritoCompra(carrito);
                    nuevo.setProducto(producto);
                    nuevo.setCarritoCantidad(0);
                    return nuevo;
                });

        carritoProducto.setCarritoCantidad(carritoProducto.getCarritoCantidad() + cantidad);
        carritoProducto.setCarritoProductoFechaActualizacion(LocalDateTime.now());
        carritoProductoRepository.save(carritoProducto);

        carrito.setCarritoFechaActualizacion(LocalDateTime.now());
        carritoCompraRepository.save(carrito);

        List<CarritoProducto> productos = carritoProductoRepository.findByCarritoCompra(carrito);

        return carritoMapper.toCarritoResponse(carrito, productos);
    }

    @Override
    @Transactional
    public CarritoResponse eliminarProducto(Long usuarioId, Long productoId) {

        CarritoCompra carrito = carritoCompraRepository
                .findByUsuarioAndEstadoCarrito(
                        usuarioRepository.findById(usuarioId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")),
                        estadoCarritoRepository.findByNombre(ESTADO_ABIERTO)
                                .orElseThrow(() -> new RuntimeException("Estado 'ABIERTO' no encontrado"))
                ).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        CarritoProducto carritoProducto = carritoProductoRepository
                .findByCarritoCompraAndProducto(carrito, productoRepository.findById(productoId)
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado")))
                .orElseThrow(() -> new RuntimeException("Product no encontrado en el carrito"));

        carritoProductoRepository.delete(carritoProducto);

        carrito.setCarritoFechaActualizacion(LocalDateTime.now());
        carritoCompraRepository.save(carrito);

        List<CarritoProducto> productos = carritoProductoRepository.findByCarritoCompra(carrito);

        return carritoMapper.toCarritoResponse(carrito, productos);
    }

    @Override
    @Transactional
    public CarritoResponse actualizarCantidadProducto(Long usuarioId, Long productoId, Integer cantidad) {

        if(cantidad < 0){
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EstadoCarrito estadoAbierto = estadoCarritoRepository.findByNombre(ESTADO_ABIERTO)
                .orElseThrow(() -> new RuntimeException("Estado ABIERO no encontrado"));

        CarritoCompra carrito = carritoCompraRepository
                .findByUsuarioAndEstadoCarrito(usuario, estadoAbierto)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Poducto no encontrado"));

        Optional<CarritoProducto> carritoProductoOpt =
                carritoProductoRepository.findByCarritoCompraAndProducto(carrito, producto);

        if(carritoProductoOpt.isEmpty()){
            throw new RuntimeException("El producto no está en el carrito");
        }

        CarritoProducto carritoProducto = carritoProductoOpt.get();

        if (cantidad == 0) {
            carritoProductoRepository.delete(carritoProducto);
        } else {
            if (producto.getProductoStock() < cantidad) {
                throw new RuntimeException("Stock insuficiente");
            }

            carritoProducto.setCarritoCantidad(cantidad);
            carritoProducto.setCarritoProductoFechaActualizacion(LocalDateTime.now());
            carritoProductoRepository.save(carritoProducto);
        }

        carrito.setCarritoFechaActualizacion(LocalDateTime.now());
        carritoCompraRepository.save(carrito);

        List<CarritoProducto> productos = carritoProductoRepository.findByCarritoCompra(carrito);
        return carritoMapper.toCarritoResponse(carrito, productos);
    }

    @Override
    public CarritoResponse vaciarCarrito(Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EstadoCarrito estadoAbierto = estadoCarritoRepository.findByNombre(ESTADO_ABIERTO)
                .orElseThrow(() -> new RuntimeException("Estado ABIERTO no encontrado"));

        CarritoCompra carrito = carritoCompraRepository.findByUsuarioAndEstadoCarrito(usuario, estadoAbierto)
                .orElseThrow(() -> new RuntimeException("Carrito activo no encontrado"));

        List<CarritoProducto> productos = carritoProductoRepository.findByCarritoCompra(carrito);
        carritoProductoRepository.deleteAll(productos);

        carrito.setCarritoFechaActualizacion(LocalDateTime.now());
        carritoCompraRepository.save(carrito);

        return carritoMapper.toCarritoResponse(carrito, List.of());
    }


}
