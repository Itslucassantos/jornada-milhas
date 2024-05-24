package com.jornadamilhas.services.implement;

import com.jornadamilhas.dtos.DepositionsDataDto;
import com.jornadamilhas.models.DepositionsModel;
import com.jornadamilhas.repositories.IDepositionsRepository;
import com.jornadamilhas.services.IDepositionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepositionsServiceImpl implements IDepositionsService {

    private final IDepositionsRepository iDepositionsRepository;

    @Autowired
    public DepositionsServiceImpl(IDepositionsRepository iDepositionsRepository) {
        this.iDepositionsRepository = iDepositionsRepository;
    }

    @Override
    public DepositionsDataDto save(@Valid DepositionsDataDto data) {
        DepositionsModel depositionsModel = new DepositionsModel(data);
        this.iDepositionsRepository.save(depositionsModel);
        return new DepositionsDataDto(depositionsModel);
    }

    @Override
    public Optional<DepositionsModel> findById(UUID depositionId) {
        return this.iDepositionsRepository.findById(depositionId);
    }

    @Override
    public void delete(DepositionsModel depositionsModel) {
        this.iDepositionsRepository.delete(depositionsModel);
    }

    @Override
    public DepositionsModel update(DepositionsModel depositionsModel) {
        this.iDepositionsRepository.save(depositionsModel);
        return depositionsModel;
    }

    @Override
    public List<DepositionsModel> getTheLastThree() {
        return this.iDepositionsRepository.getTheLastThree();
    }

}
