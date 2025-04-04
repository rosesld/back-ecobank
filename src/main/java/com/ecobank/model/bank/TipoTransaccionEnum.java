package com.ecobank.model.bank;

public enum TipoTransaccionEnum {
    DEPOSITO("depósito"),
    PAGO("pago"),
    TRANSFERENCIA("transferencia");

    private String descripcion;

    TipoTransaccionEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
