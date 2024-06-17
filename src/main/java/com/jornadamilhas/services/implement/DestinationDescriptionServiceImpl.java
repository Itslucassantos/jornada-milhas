package com.jornadamilhas.services.implement;

import com.jornadamilhas.dtos.DestinationDescriptionDto;
import com.jornadamilhas.models.DestinationDescriptionModel;
import com.jornadamilhas.models.DestinationModel;
import com.jornadamilhas.repositories.IDestinationDescriptionRepository;
import com.jornadamilhas.repositories.IDestinationRepository;
import com.jornadamilhas.services.IDestinationDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class DestinationDescriptionServiceImpl implements IDestinationDescriptionService {

    private final IDestinationDescriptionRepository iDestinationDescriptionRepository;

    private final IDestinationRepository iDestinationRepository;

    @Autowired
    public DestinationDescriptionServiceImpl(IDestinationDescriptionRepository iDestinationDescriptionRepository, IDestinationRepository iDestinationRepository) {
        this.iDestinationDescriptionRepository = iDestinationDescriptionRepository;
        this.iDestinationRepository = iDestinationRepository;
    }

    @Override
    public DestinationDescriptionModel save(DestinationDescriptionDto data) {
        DestinationDescriptionModel descriptionModel = null;
        try {
            descriptionModel = new DestinationDescriptionModel(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DestinationDescriptionModel descriptionModelSaved = this.iDestinationDescriptionRepository.save(descriptionModel);
        Optional<DestinationModel> destinationModelOptional = this.iDestinationRepository.findById(data.getDestinationId());
        DestinationModel destinationModel = destinationModelOptional.get();
        destinationModel.setDestinationDescriptionModelId(descriptionModelSaved.getDestinationDescriptionId());
        this.iDestinationRepository.save(destinationModel);
        return descriptionModelSaved;
    }

    @Override
    public Optional<DestinationDescriptionModel> findById(UUID destinationDescriptionId) {
        return this.iDestinationDescriptionRepository.findById(destinationDescriptionId);
    }

    @Override
    public void delete(DestinationDescriptionModel descriptionModel) {
        Optional<DestinationModel> destinationModelOptional = this.iDestinationRepository.findById(descriptionModel.getDestinationId());
        DestinationModel destinationModel = destinationModelOptional.get();
        destinationModel.setDestinationDescriptionModelId(null);
        this.iDestinationRepository.save(destinationModel);
        this.iDestinationDescriptionRepository.delete(descriptionModel);
    }

}
