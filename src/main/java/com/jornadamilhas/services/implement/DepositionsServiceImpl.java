package com.jornadamilhas.services.implement;

import com.jornadamilhas.repositories.IDepositionsRepository;
import com.jornadamilhas.services.IDepositionsService;
import org.springframework.beans.factory.annotation.Autowired;

public class DepositionsServiceImpl implements IDepositionsService {

    private final IDepositionsRepository iDepositionsRepository;

    @Autowired
    public DepositionsServiceImpl(IDepositionsRepository iDepositionsRepository) {
        this.iDepositionsRepository = iDepositionsRepository;
    }

}
