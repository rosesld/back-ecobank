package com.ecobank.bank.mapper;

import com.ecobank.bank.dto.response.TransaccionBancariaResponse;
import com.ecobank.bank.model.CuentaBancaria;
import com.ecobank.bank.model.Estado;
import com.ecobank.bank.model.TipoTransaccionEnum;
import com.ecobank.bank.model.TransaccionBancaria;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TransaccionBancariaMapper {

    public TransaccionBancaria toEntity(CuentaBancaria origen, CuentaBancaria destino, BigDecimal monto, TipoTransaccionEnum tipo, Estado estado) {
        TransaccionBancaria transaccionBancaria = new TransaccionBancaria();
        transaccionBancaria.setCuentaOrigen(origen);
        transaccionBancaria.setCuentaDestino(destino);
        transaccionBancaria.setMonto(monto);
        transaccionBancaria.setTipoTransaccion(tipo);
        transaccionBancaria.setEstado(estado);
        transaccionBancaria.setFechaTransaccion(LocalDateTime.now());
        transaccionBancaria.setFechaActualizacion(LocalDateTime.now());
        return transaccionBancaria;
    }

    public TransaccionBancariaResponse toResponse(TransaccionBancaria transaccion) {
        TransaccionBancariaResponse response = new TransaccionBancariaResponse();

        if (transaccion.getCuentaOrigen() != null) {
            response.setCuentaOrigen(transaccion.getCuentaOrigen().getNumeroDeCuenta());

            if (transaccion.getCuentaOrigen().getClienteBancario() != null) {
                response.setRut(transaccion.getCuentaOrigen().getClienteBancario().getRutClienteBancario());
            }
        }

        response.setCuentaDestino(transaccion.getCuentaDestino().getNumeroDeCuenta());
        response.setMonto(transaccion.getMonto());
        response.setTipoTransaccion(transaccion.getTipoTransaccion().name());
        response.setFechaTransaccion(transaccion.getFechaTransaccion());

        return response;
    }
}
