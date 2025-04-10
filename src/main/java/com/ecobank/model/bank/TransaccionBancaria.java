package com.ecobank.model.bank;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaciones_bancarias",
    indexes = {
        @Index(name = "idx_transaccion_cuenta_origen", columnList = "cuenta_origen"),
        @Index(name = "idx_transaccion_cuentfa_destino", columnList = "cuenta_destino")
    }
)
public class TransaccionBancaria {

    @Id
    @Column(name = "transaccion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaccionId;

    @Column(name = "monto", nullable = false)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transaccion", nullable = false)
    private TipoTransaccionEnum tipoTransaccion;

    @Column(name = "fecha_transaccion", nullable = false, updatable = false)
    private LocalDateTime fechaTransaccion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen", referencedColumnName = "cuenta_id", nullable = false)
    private CuentaBancaria cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino", referencedColumnName = "cuenta_id", nullable = false)
    private CuentaBancaria cuentaDestino;

    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "estado_id", nullable = false)
    private Estado estado;

    public TransaccionBancaria() {
    }

    public TransaccionBancaria(BigDecimal monto, TipoTransaccionEnum tipoTransaccion, LocalDateTime fechaTransaccion, LocalDateTime fechaActualizacion) {
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.fechaTransaccion = fechaTransaccion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getTransaccionId() {
        return transaccionId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public TipoTransaccionEnum getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccionEnum tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public CuentaBancaria getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(CuentaBancaria cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public CuentaBancaria getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(CuentaBancaria cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransaccionBancaria{");
        sb.append("transaccionId=").append(transaccionId);
        sb.append(", monto=").append(monto);
        sb.append(", tipoTransaccion=").append(tipoTransaccion);
        sb.append(", fechaTransaccion=").append(fechaTransaccion);
        sb.append(", fechaActualizacion=").append(fechaActualizacion);
        sb.append('}');
        return sb.toString();
    }
}
