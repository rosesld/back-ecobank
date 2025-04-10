package com.ecobank.model.bank;

import com.ecobank.model.auth.Usuario;
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

    @Column(name = "requiere_activacion")
    private Boolean requiereActivacion;

    @Column(name = "fecha_activacion")
    private LocalDateTime fechaActivacion;

    @Column(name = "documento_identidad", length = 50)
    private String documentoIdentidad;

    @Column(name = "telefono_verificado")
    private Boolean telefonoVerificado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "estado_id", nullable = false)
    private Estado estado;

    public CuentaBancaria() {
    }

    public CuentaBancaria(Long cuentaId, BigDecimal saldo, int numeroDeCuenta, TipoCuentaEnum tipoCuenta, Boolean requiereActivacion, LocalDateTime fechaActivacion, String documentoIdentidad, Boolean telefonoVerificado, LocalDateTime fechaCreacion) {
        this.cuentaId = cuentaId;
        this.saldo = saldo;
        this.numeroDeCuenta = numeroDeCuenta;
        this.tipoCuenta = tipoCuenta;
        this.requiereActivacion = requiereActivacion;
        this.fechaActivacion = fechaActivacion;
        this.documentoIdentidad = documentoIdentidad;
        this.telefonoVerificado = telefonoVerificado;
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

    public Boolean getRequiereActivacion() {
        return requiereActivacion;
    }

    public void setRequiereActivacion(Boolean requiereActivacion) {
        this.requiereActivacion = requiereActivacion;
    }

    public LocalDateTime getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(LocalDateTime fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public Boolean getTelefonoVerificado() {
        return telefonoVerificado;
    }

    public void setTelefonoVerificado(Boolean telefonoVerificado) {
        this.telefonoVerificado = telefonoVerificado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CuentaBancaria{");
        sb.append("cuentaId=").append(cuentaId);
        sb.append(", saldo=").append(saldo);
        sb.append(", numeroDeCuenta=").append(numeroDeCuenta);
        sb.append(", tipoCuenta=").append(tipoCuenta);
        sb.append(", requiereActivacion=").append(requiereActivacion);
        sb.append(", fechaActivacion=").append(fechaActivacion);
        sb.append(", documentoIdentidad='").append(documentoIdentidad).append('\'');
        sb.append(", telefonoVerificado=").append(telefonoVerificado);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append('}');
        return sb.toString();
    }
}
