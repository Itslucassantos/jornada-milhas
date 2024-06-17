package com.jornadamilhas.repositories;

import com.jornadamilhas.models.DestinationDescriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDestinationDescriptionRepository extends JpaRepository<DestinationDescriptionModel, UUID> {
}
