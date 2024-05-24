package com.jornadamilhas.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.jornadamilhas.dtos.DepositionsDataDto;
import com.jornadamilhas.models.DepositionsModel;
import com.jornadamilhas.services.IDepositionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/saveDeposition")
    @Transactional
    public ResponseEntity<Object> save(@RequestBody @Valid DepositionsDataDto data) {
        try {
            DepositionsDataDto dataSaved = this.iDepositionsService.save(data);
            DepositionsModel depositionsModel = new DepositionsModel(dataSaved);
            return ResponseEntity.status(HttpStatus.CREATED).body(depositionsModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getOneDeposition/{depositionId}")
    @Transactional
    public ResponseEntity<Object> getOneDeposition(@PathVariable(value = "depositionId") UUID depositionId) {
        Optional<DepositionsModel> depositionsModelOptional = this.iDepositionsService.findById(depositionId);
        if (!depositionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Deposition not found! ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(depositionsModelOptional.get());
    }

    @DeleteMapping("/deleteDeposition/{depositionId}")
    @Transactional
    public ResponseEntity<Object> deleteDeposition(@PathVariable(value = "depositionId") UUID depositionId) {
        Optional<DepositionsModel> depositionsModelOptional = this.iDepositionsService.findById(depositionId);
        if (!depositionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Deposition not found! ");
        }
        this.iDepositionsService.delete(depositionsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Deposition deleted successfully! ");
    }

    @PutMapping("/updateDeposition")
    @Transactional
    public ResponseEntity<Object> updateDeposition(@RequestBody @Validated(DepositionsDataDto.DepositionsView.DepositionsPut.class)
                                                   @JsonView(DepositionsDataDto.DepositionsView.DepositionsPut.class) DepositionsDataDto data) {
        Optional<DepositionsModel> depositionsModelOptional = this.iDepositionsService.findById(data.getDepositionId());
        if (!depositionsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Deposition not found. ");
        } else {
            DepositionsModel depositionsModel = depositionsModelOptional.get();
            depositionsModel.setTestimony(data.getTestimony());
            depositionsModel.setImageUrl(data.getImageUrl());
            this.iDepositionsService.update(depositionsModel);
            return ResponseEntity.status(HttpStatus.OK).body(depositionsModel);
        }
    }


}
