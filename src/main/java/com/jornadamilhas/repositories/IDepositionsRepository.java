package com.jornadamilhas.repositories;

import com.jornadamilhas.models.DepositionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IDepositionsRepository extends JpaRepository<DepositionsModel, UUID> {

    @Query(value = "SELECT * FROM depositions ORDER BY deposition_id DESC LIMIT 3", nativeQuery = true)
    List<DepositionsModel> getTheLastThree();

}
