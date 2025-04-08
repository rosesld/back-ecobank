package com.ecobank.social.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fundaciones")
public class Fundacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fundacion_id")
    private Long FundacionId;

    @Column(name = "nombre_fundacion")
    private String nombreFundacion;

    @Column(name = "descripcion_fundacion", length = 500)
    private String descripcionFundacion;

    public Fundacion() {
    }

    public Fundacion(Long fundacionId, String nombreFundacion, String descripcionFundacion) {
        FundacionId = fundacionId;
        this.nombreFundacion = nombreFundacion;
        this.descripcionFundacion = descripcionFundacion;
    }

    public String getDescripcionFundacion() {
        return descripcionFundacion;
    }

    public void setDescripcionFundacion(String descripcionFundacion) {
        this.descripcionFundacion = descripcionFundacion;
    }

    public Long getFundacionId() {
        return FundacionId;
    }

    public void setFundacionId(Long fundacionId) {
        FundacionId = fundacionId;
    }

    public String getNombreFundacion() {
        return nombreFundacion;
    }

    public void setNombreFundacion(String nombreFundacion) {
        this.nombreFundacion = nombreFundacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fundacion{");
        sb.append("FundacionId=").append(FundacionId);
        sb.append(", nombreFundacion='").append(nombreFundacion).append('\'');
        sb.append(", descripcionFundacion='").append(descripcionFundacion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
