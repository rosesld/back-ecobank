package com.ecobank.commerce.dto.response;

public class DireccionEnvioResponse {

    private Integer direccionEnvioId;
    private String calle;
    private Integer numero;
    private String nota;
    private String region;  // Aquí asumo que la región es un String o el nombre de la región
    private String comuna;  // Lo mismo para comuna

    public DireccionEnvioResponse(Integer direccionEnvioId, String calle, Integer numero, String nota, String region, String comuna) {
        this.direccionEnvioId = direccionEnvioId;
        this.calle = calle;
        this.numero = numero;
        this.nota = nota;
        this.region = region;
        this.comuna = comuna;
    }

    // Getters y Setters
    public Integer getDireccionEnvioId() {
        return direccionEnvioId;
    }

    public void setDireccionEnvioId(Integer direccionEnvioId) {
        this.direccionEnvioId = direccionEnvioId;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
}
