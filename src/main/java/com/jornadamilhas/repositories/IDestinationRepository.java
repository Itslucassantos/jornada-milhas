package com.jornadamilhas.repositories;

import com.jornadamilhas.models.DestinationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IDestinationRepository extends JpaRepository<DestinationModel, UUID> {
    Optional<DestinationModel> findByName(String name);

    Optional<DestinationModel> findDestinationByName(String name);
}
