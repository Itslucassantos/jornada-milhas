package com.jornadamilhas.repositories;

import com.jornadamilhas.models.DepositionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IDepositionsRepository extends JpaRepository<DepositionsModel, UUID> {

    // Utilizando JPQL na @Query para poder buscar em qualquer banco de dados.
    @Query("SELECT d FROM DepositionsModel d ORDER BY d.depositionId DESC LIMIT 3")
    List<DepositionsModel> getTheLastThree();

}
