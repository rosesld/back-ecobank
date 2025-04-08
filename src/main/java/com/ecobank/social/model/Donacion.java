package com.ecobank.social.model;

import com.ecobank.auth.model.Usuario;
import com.ecobank.commerce.model.Pedido;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "donaciones")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donacion_id")
    private Long donacionId;

    @Column(name = "monto_donacion", nullable = true, updatable = false)
    private BigDecimal montoDonacion;

    @Column(name = "fecha_donacion", nullable = true, updatable = false)
    private LocalDateTime fechaDonacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "fundacion_id", referencedColumnName = "fundacion_id", nullable = false)
    private Fundacion fundacion;

    public Donacion() {
    }

    public Donacion(Long id, BigDecimal monto, LocalDateTime fechaDonacion) {
        this.donacionId = id;
        this.montoDonacion = monto;
        this.fechaDonacion = fechaDonacion;
    }

    public Long getDonacionId() {
        return donacionId;
    }

    public void setDonacionId(Long donacionId) {
        this.donacionId = donacionId;
    }

    public BigDecimal getMontoDonacion() {
        return montoDonacion;
    }

    public void setMontoDonacion(BigDecimal montoDonacion) {
        this.montoDonacion = montoDonacion;
    }

    public LocalDateTime getFechaDonacion() {
        return fechaDonacion;
    }

    public void setFechaDonacion(LocalDateTime fechaDonacion) {
        this.fechaDonacion = fechaDonacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Fundacion getFundacion() {
        return fundacion;
    }

    public void setFundacion(Fundacion fundacion) {
        this.fundacion = fundacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Donacion{");
        sb.append("donacionId=").append(donacionId);
        sb.append(", montoDonacion=").append(montoDonacion);
        sb.append(", fechaDonacion=").append(fechaDonacion);
        sb.append('}');
        return sb.toString();
    }
}
