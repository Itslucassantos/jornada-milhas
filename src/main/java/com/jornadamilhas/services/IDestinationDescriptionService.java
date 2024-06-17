package com.jornadamilhas.services;

import com.jornadamilhas.dtos.DestinationDescriptionDto;
import com.jornadamilhas.models.DestinationDescriptionModel;

import java.util.Optional;
import java.util.UUID;

public interface IDestinationDescriptionService {
    DestinationDescriptionModel save(DestinationDescriptionDto data);

    Optional<DestinationDescriptionModel> findById(UUID destinationDescriptionId);

    void delete(DestinationDescriptionModel descriptionModel);
}
