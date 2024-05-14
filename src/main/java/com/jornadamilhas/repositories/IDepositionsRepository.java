package com.jornadamilhas.repositories;

import com.jornadamilhas.models.DepositionsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDepositionsRepository extends JpaRepository<DepositionsModel, UUID> {
}
