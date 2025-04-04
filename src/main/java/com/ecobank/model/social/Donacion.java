package com.ecobank.model.social;

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
