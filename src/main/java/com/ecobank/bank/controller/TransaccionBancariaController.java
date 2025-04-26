package com.ecobank.bank.controller;

import com.ecobank.bank.dto.request.DepositoBancarioDTO;
import com.ecobank.bank.dto.request.TransferenciaBancariaDTO;
import com.ecobank.bank.dto.response.TransaccionBancariaResponse;
import com.ecobank.bank.model.TransaccionBancaria;
import com.ecobank.bank.service.impl.TransaccionBancariaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionBancariaController {

    private final TransaccionBancariaServiceImpl transaccionBancariaServiceImpl;

    public TransaccionBancariaController(TransaccionBancariaServiceImpl transaccionBancariaServiceImpl) {
        this.transaccionBancariaServiceImpl = transaccionBancariaServiceImpl;
    }

    @PostMapping("/transferencia")
    public ResponseEntity<TransaccionBancariaResponse> realizarTransferencia(@RequestBody TransferenciaBancariaDTO transferenciaBancariaDTO){
        TransaccionBancariaResponse response = transaccionBancariaServiceImpl.realizarTransaccion(transferenciaBancariaDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposito")
    public ResponseEntity<TransaccionBancariaResponse> realizaDeposito(@RequestBody DepositoBancarioDTO depositoBancarioDTO){
        TransaccionBancariaResponse response = transaccionBancariaServiceImpl.realizarDeposito(depositoBancarioDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hostorial/{numeroCuenta}")
    public ResponseEntity<List<TransaccionBancariaResponse>> historial(@PathVariable String numeroCuenta){
        List<TransaccionBancariaResponse> response = transaccionBancariaServiceImpl.obtenerHistorialTransacciones(numeroCuenta);
        return ResponseEntity.ok(response);
    }
}
