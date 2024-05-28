package com.jornadamilhas.controllers;

import com.jornadamilhas.services.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/destinos")
public class DestinationController {

    private final IDestinationService iDestinationService;

    @Autowired
    public DestinationController(IDestinationService iDestinationService) {
        this.iDestinationService = iDestinationService;
    }

}
