package com.ecobank.bank.model;

import com.ecobank.auth.model.Usuario;
import com.ecobank.commerce.model.Pedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id")
    private Long pagoId;

    @Column(name = "pago_monto", precision = 10, scale = 2)
    private BigDecimal pagoMonto;

    @Column(name = "pago_fecha", nullable = false, updatable = false)
    private LocalDateTime pagoFecha;

    @Column(name = "pago_fecha_actualizacion", nullable = false)
    private LocalDateTime pagoFechaActulizacion;

    //TODO: RELACION CON LA TABLA PEDIDO, traer llave foranea
    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "pedido_id", nullable = false)
    private Pedido pedido;

    //TODO: RELACION CON LA TABLA ESTADO, traer llave foranea
    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "estado_id", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "cuenta_bancaria_id", referencedColumnName = "cuenta_id", nullable = false)
    private CuentaBancaria cuentaBancaria;

    public Pago (){}

    public Pago(Long pagoId, BigDecimal pagoMonto, LocalDateTime pagoFecha, LocalDateTime pagoFechaActulizacion, Pedido pedido, Estado estado, CuentaBancaria cuentaBancaria) {
        this.pagoId = pagoId;
        this.pagoMonto = pagoMonto;
        this.pagoFecha = pagoFecha;
        this.pagoFechaActulizacion = pagoFechaActulizacion;
        this.pedido = pedido;
        this.estado = estado;
        this.cuentaBancaria = cuentaBancaria;
    }

    @PrePersist
    public void prePersist() {
        // Se establece la fecha de registro solo al crear el usuario
        if(this.pagoFecha == null) {
            this.pagoFecha = LocalDateTime.now();
        }
    }

    public Long getPagoId() {
        return pagoId;
    }

    public void setPagoId(Long pagoId) {
        this.pagoId = pagoId;
    }

    public BigDecimal getPagoMonto() {
        return pagoMonto;
    }

    public void setPagoMonto(BigDecimal pagoMonto) {
        this.pagoMonto = pagoMonto;
    }

    public LocalDateTime getPagoFecha() {
        return pagoFecha;
    }

    public void setPagoFecha(LocalDateTime pagoFecha) {
        this.pagoFecha = pagoFecha;
    }

    public LocalDateTime getPagoFechaActulizacion() {
        return pagoFechaActulizacion;
    }

    public void setPagoFechaActulizacion(LocalDateTime pagoFechaActulizacion) {
        this.pagoFechaActulizacion = pagoFechaActulizacion;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pago{");
        sb.append("pagoId=").append(pagoId);
        sb.append(", pagoMonto=").append(pagoMonto);
        sb.append(", pagoFecha=").append(pagoFecha);
        sb.append(", pagoFechaActulizacion=").append(pagoFechaActulizacion);
        sb.append(", pedido=").append(pedido);
        sb.append(", estado=").append(estado);
        sb.append(", cuentaBancaria=").append(cuentaBancaria);
        sb.append('}');
        return sb.toString();
    }
}
