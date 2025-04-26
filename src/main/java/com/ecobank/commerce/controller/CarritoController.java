package com.ecobank.commerce.controller;

import com.ecobank.commerce.dto.response.CarritoResponse;
import com.ecobank.commerce.dto.response.CompraResponse;
import com.ecobank.commerce.service.impl.CarritoCompraServiceImpl;
import com.ecobank.commerce.service.impl.ConfirmarCompraServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoCompraServiceImpl carritoService;
    private final ConfirmarCompraServiceImpl confirmarCompraService;

    public CarritoController(CarritoCompraServiceImpl carritoService, ConfirmarCompraServiceImpl confirmarCompraService) {
        this.carritoService = carritoService;
        this.confirmarCompraService = confirmarCompraService;
    }

    @GetMapping("usuario/{usuarioId}")
    public ResponseEntity<CarritoResponse> obtenerCarrito(@PathVariable Long usuarioId) {
        CarritoResponse response = carritoService.obtenerCarritoActivo(usuarioId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/usuario/{usuarioId}/producto/{productoId}")
    public ResponseEntity<CarritoResponse> agregarProducto(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId,
            @RequestParam(defaultValue = "1") Integer cantidad

    ) {

        CarritoResponse response = carritoService.agregarProducto(usuarioId, productoId, cantidad);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/usuario/{usuarioId}/producto/{productoId}")
    public ResponseEntity<CarritoResponse> eliminarProducto(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId
    ){
        CarritoResponse response = carritoService.eliminarProducto(usuarioId, productoId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/usuario/{usuarioId}/producto/{productoId}")
    public ResponseEntity<CarritoResponse> actualizarCantidad(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId,
            @RequestParam("cantidad") Integer cantidad

    ){
        CarritoResponse response = carritoService.actualizarCantidadProducto(usuarioId, productoId, cantidad);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/usuario/{usuarioId}/vaciar")
    public ResponseEntity<CarritoResponse> vaciarCarrito(@PathVariable Long usuarioId){
        CarritoResponse response = carritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/usuario/{usuarioId}/confirmar")
    public ResponseEntity<CompraResponse> confirmarCompra(
            @PathVariable Long usuarioId,
            @RequestParam Long direccionEnvio
    ){
        CompraResponse response = confirmarCompraService.confirmarCompra(usuarioId, direccionEnvio);
        return ResponseEntity.ok(response);
    }
}
