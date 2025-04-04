package com.ecobank.model.bank;

public enum TipoCuentaEnum {
    VISTA("vista");

    private String tipo;

    TipoCuentaEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
