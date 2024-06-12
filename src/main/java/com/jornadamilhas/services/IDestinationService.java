package com.jornadamilhas.services;

import com.jornadamilhas.models.DestinationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IDestinationService {
    DestinationModel save(DestinationModel destinationModel);

    Optional<DestinationModel> findByName(String name);

    Optional<DestinationModel> findById(UUID depositionId);

    void delete(DestinationModel destinationModel);

    Page<DestinationModel> findAll(Pageable pageable);
}
