package com.jornadamilhas.services.implement;

import com.jornadamilhas.repositories.IDestinationRepository;
import com.jornadamilhas.services.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationServiceImpl implements IDestinationService {

    private final IDestinationRepository iDestinationRepository;

    @Autowired
    public DestinationServiceImpl(IDestinationRepository iDestinationRepository) {
        this.iDestinationRepository = iDestinationRepository;
    }

}
