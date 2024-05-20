package com.jornadamilhas.services;

import com.jornadamilhas.dtos.DepositionsDataDto;
import com.jornadamilhas.models.DepositionsModel;

import java.util.Optional;
import java.util.UUID;

public interface IDepositionsService {
    DepositionsDataDto save(DepositionsDataDto data);

    Optional<DepositionsModel> findById(UUID depositionId);

    void delete(DepositionsModel depositionsModel);
}
