package com.ecobank.bank.service.services;

import com.ecobank.bank.dto.request.DepositoBancarioDTO;
import com.ecobank.bank.dto.request.TransferenciaBancariaDTO;
import com.ecobank.bank.dto.response.TransaccionBancariaResponse;

import java.util.List;

public interface TransaccionBancariaService{
    TransaccionBancariaResponse realizarTransaccion(TransferenciaBancariaDTO transferenciaBancariaDTO);
    TransaccionBancariaResponse realizarDeposito(DepositoBancarioDTO depositoBancarioDTO);
    List<TransaccionBancariaResponse> obtenerHistorialTransacciones(String numeroDeCuenta);
}
