package com.ecobank.bank.service.impl;

import com.ecobank.bank.dto.request.DepositoBancarioDTO;
import com.ecobank.bank.dto.request.TransferenciaBancariaDTO;
import com.ecobank.bank.dto.response.TransaccionBancariaResponse;
import com.ecobank.bank.mapper.TransaccionBancariaMapper;
import com.ecobank.bank.model.CuentaBancaria;
import com.ecobank.bank.model.Estado;
import com.ecobank.bank.model.TipoTransaccionEnum;
import com.ecobank.bank.model.TransaccionBancaria;
import com.ecobank.bank.repository.CuentaBancariaRepository;
import com.ecobank.bank.repository.EstadoRepository;
import com.ecobank.bank.repository.TransaccionBancariaRepository;
import com.ecobank.bank.service.services.TransaccionBancariaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransaccionBancariaServiceImpl implements TransaccionBancariaService {

    private final TransaccionBancariaRepository transaccionBancariaRepository;
    private final CuentaBancariaRepository cuentaBancariaRepository;
    private final EstadoRepository estadoRepository;
    private final TransaccionBancariaMapper transaccionBancariaMapper;

    public TransaccionBancariaServiceImpl(TransaccionBancariaRepository transaccionBancariaRepository, CuentaBancariaRepository cuentaBancariaRepository, EstadoRepository estadoRepository, TransaccionBancariaMapper transaccionBancariaMapper) {
        this.transaccionBancariaRepository = transaccionBancariaRepository;
        this.cuentaBancariaRepository = cuentaBancariaRepository;
        this.estadoRepository = estadoRepository;
        this.transaccionBancariaMapper = transaccionBancariaMapper;
    }


    @Override
    public TransaccionBancariaResponse realizarTransaccion(TransferenciaBancariaDTO transferenciaBancariaDTO) {

        if(transferenciaBancariaDTO.getMonto() == null || transferenciaBancariaDTO.getMonto()
                .compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El monto de la transferencia debe ser mayor a cero");
        }

        CuentaBancaria origen = cuentaBancariaRepository.findByNumeroDeCuenta(transferenciaBancariaDTO.getCuentaOrigen())
                .orElseThrow(()-> new RuntimeException("No existe el cuenta de origen"));


        CuentaBancaria destino = cuentaBancariaRepository.findByNumeroDeCuenta(transferenciaBancariaDTO.getCuentaDestino())
                .orElseThrow(()-> new RuntimeException("No existe el cuenta de destino"));


        if(origen.getSaldo().compareTo(transferenciaBancariaDTO.getMonto()) < 0){
            throw new IllegalArgumentException("Monto insuficiente");
        }

        origen.setSaldo(origen.getSaldo().subtract(transferenciaBancariaDTO.getMonto()));
        destino.setSaldo(destino.getSaldo().add(transferenciaBancariaDTO.getMonto()));
        cuentaBancariaRepository.saveAll(List.of(origen, destino));

        Estado activo = estadoRepository.findByEstadoNombre("ACTIVO")
                .orElseThrow(()-> new RuntimeException("No existe el estado"));

        TransaccionBancaria transaccionBancaria = transaccionBancariaMapper.toEntity(
                origen,
                destino,
                transferenciaBancariaDTO.getMonto(),
                TipoTransaccionEnum.TRANSFERENCIA,
                activo);
        transaccionBancariaRepository.save(transaccionBancaria);

        return transaccionBancariaMapper.toResponse(transaccionBancaria);
    }

    @Override
    public TransaccionBancariaResponse realizarDeposito(DepositoBancarioDTO depositoBancarioDTO) {

        if (depositoBancarioDTO.getMonto() == null || depositoBancarioDTO.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }

        CuentaBancaria cuenta = cuentaBancariaRepository.findByNumeroDeCuenta(depositoBancarioDTO.getCuentaDestino())
                .orElseThrow(()-> new RuntimeException("No existe el cuenta de destino"));

        cuenta.setSaldo(cuenta.getSaldo().add(depositoBancarioDTO.getMonto()));
        cuentaBancariaRepository.save(cuenta);

        Estado activo = estadoRepository.findByEstadoNombre("ACTIVO")
                .orElseThrow(()-> new RuntimeException("No existe el estado"));

        TransaccionBancaria transaccionBancaria = transaccionBancariaMapper.toEntity(
                null,
                cuenta,
                depositoBancarioDTO.getMonto(),
                TipoTransaccionEnum.DEPOSITO,
                activo
        );

        transaccionBancariaRepository.save(transaccionBancaria);

        return transaccionBancariaMapper.toResponse(transaccionBancaria);
    }

    @Override
    public List<TransaccionBancariaResponse> obtenerHistorialTransacciones(String numeroDeCuenta) {

        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findByNumeroDeCuenta(numeroDeCuenta)
                .orElseThrow(()-> new RuntimeException("Cuenat no encontrada"));

        List<TransaccionBancaria> transacciones = transaccionBancariaRepository.findByCuentaOrigenOrCuentaDestino(cuentaBancaria, cuentaBancaria);

        return transacciones.stream()
                .map(transaccionBancariaMapper::toResponse)
                .toList();
    }
}
