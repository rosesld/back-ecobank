package com.ecobank.social.controller;

import com.ecobank.social.model.Fundacion;
import com.ecobank.social.service.impl.FundacionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FundacionController {

    private final FundacionServiceImpl fundacionServiceImpl;

    public FundacionController(FundacionServiceImpl fundacionServiceImpl) {
        this.fundacionServiceImpl = fundacionServiceImpl;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Fundacion> saveFundacion(@RequestBody Fundacion fundacion) {
        return new ResponseEntity<>(fundacionServiceImpl.saveFundacion(fundacion), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Fundacion>> findAllFundacion(){
        return ResponseEntity.ok(fundacionServiceImpl.allFundacion());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Fundacion> findByIdFundacion(@PathVariable Long id) {
        return ResponseEntity.ok(fundacionServiceImpl.findByIdFundacion(id));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Fundacion> updateFundacion(@PathVariable Long id, @RequestBody Fundacion fundacion){
        return ResponseEntity.ok(fundacionServiceImpl.updateFundacion(id, fundacion));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteFundacion(@PathVariable Long id) {
        fundacionServiceImpl.deleteFundacion(id);
        return new ResponseEntity<>("La fundacion ha sido Eliminada", HttpStatus.OK);
    }
}
