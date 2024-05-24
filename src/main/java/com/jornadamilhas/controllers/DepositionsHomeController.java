package com.jornadamilhas.controllers;

import com.jornadamilhas.models.DepositionsModel;
import com.jornadamilhas.services.IDepositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/depoimentos-home")
public class DepositionsHomeController {

    private final IDepositionsService iDepositionsService;

    @Autowired
    public DepositionsHomeController(IDepositionsService iDepositionsService) {
        this.iDepositionsService = iDepositionsService;
    }

    @GetMapping("/getTheLastThree")
    @Transactional
    public ResponseEntity<Object> getTheLastThree() {
        List<DepositionsModel> theLastThree = this.iDepositionsService.getTheLastThree();
        return ResponseEntity.status(HttpStatus.OK).body(theLastThree);
    }


}
