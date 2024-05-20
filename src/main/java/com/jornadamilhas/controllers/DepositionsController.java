package com.jornadamilhas.controllers;

import com.jornadamilhas.dtos.DepositionsDataDto;
import com.jornadamilhas.models.DepositionsModel;
import com.jornadamilhas.services.IDepositionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/depoimentos")
public class DepositionsController {

    private final IDepositionsService iDepositionsService;

    @Autowired
    public DepositionsController(IDepositionsService iDepositionsService) {
        this.iDepositionsService = iDepositionsService;
    }

    @PostMapping("/save")
    @Transactional
    public ResponseEntity<Object> save(@RequestBody @Valid DepositionsDataDto data) {
        try {
            this.iDepositionsService.save(data);
            DepositionsModel depositionsModel = new DepositionsModel(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(depositionsModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{depositionId}")
    @Transactional
    public ResponseEntity<Object> getOneDeposition(@PathVariable(value = "depositionId") UUID depositionId) {
        Optional<DepositionsModel> depositionsModelOptional = this.iDepositionsService.findById(depositionId);
        if (!depositionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Deposition not found! ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(depositionsModelOptional.get());
    }

    @DeleteMapping("/{depositionId}")
    @Transactional
    public ResponseEntity<Object> deleteDeposition(@PathVariable(value = "depositionId") UUID depositionId) {
        Optional<DepositionsModel> depositionsModelOptional = this.iDepositionsService.findById(depositionId);
        if (!depositionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Deposition not found! ");
        }
        this.iDepositionsService.delete(depositionsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Deposition deleted successfully! ");
    }


}
