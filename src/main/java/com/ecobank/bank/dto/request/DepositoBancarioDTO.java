package com.ecobank.bank.dto.request;

import java.math.BigDecimal;

public class DepositoBancarioDTO {

    private String cuentaDestino;
    private BigDecimal monto;

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
