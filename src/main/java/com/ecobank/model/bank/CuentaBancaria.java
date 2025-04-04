package com.ecobank.model.bank;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancaria {

    @Id
    @Column(name = "cuenta_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuentaId;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "numero_cuenta", nullable = false, unique = true)
    private int numeroDeCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false)
    private TipoCuentaEnum tipoCuenta;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public CuentaBancaria() {
    }

    public CuentaBancaria(BigDecimal saldo, int numeroDeCuenta, TipoCuentaEnum tipoCuenta, LocalDateTime fechaCreacion) {
        this.saldo = saldo;
        this.numeroDeCuenta = numeroDeCuenta;
        this.tipoCuenta = tipoCuenta;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(int numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public TipoCuentaEnum getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuentaEnum tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CuentaBancaria{");
        sb.append("cuentaId=").append(cuentaId);
        sb.append(", saldo=").append(saldo);
        sb.append(", numeroDeCuenta=").append(numeroDeCuenta);
        sb.append(", tipoCuenta=").append(tipoCuenta);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append('}');
        return sb.toString();
    }
}
