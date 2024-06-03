package com.jornadamilhas.services.implement;

import com.jornadamilhas.dtos.DestinationReturnDto;
import com.jornadamilhas.models.DestinationModel;
import com.jornadamilhas.repositories.IDestinationRepository;
import com.jornadamilhas.services.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DestinationServiceImpl implements IDestinationService {

    private final IDestinationRepository iDestinationRepository;

    @Autowired
    public DestinationServiceImpl(IDestinationRepository iDestinationRepository) {
        this.iDestinationRepository = iDestinationRepository;
    }

    @Override
    public DestinationModel save(DestinationModel destinationModel) {
        return this.iDestinationRepository.save(destinationModel);
    }

    @Override
    public Optional<DestinationModel> findByName(String name) {
        return this.iDestinationRepository.findByName(name);
    }

    @Override
    public Optional<DestinationModel> findById(UUID depositionId) {
        return this.iDestinationRepository.findById(depositionId);
    }

    @Override
    public void delete(DestinationModel destinationModel) {
        this.iDestinationRepository.delete(destinationModel);
    }

    @Override
    public Page<DestinationModel> findAll(Pageable pageable) {
        return this.iDestinationRepository.findAll(pageable);
    }

}
