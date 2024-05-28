package com.jornadamilhas.repositories;

import com.jornadamilhas.models.DestinationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDestinationRepository extends JpaRepository<DestinationModel, UUID> {
}
