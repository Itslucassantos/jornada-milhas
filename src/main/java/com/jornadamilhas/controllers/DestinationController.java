package com.jornadamilhas.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.jornadamilhas.dtos.DestinationDataDto;
import com.jornadamilhas.exceptions.DestinationException;
import com.jornadamilhas.models.DestinationModel;
import com.jornadamilhas.services.IDestinationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/destinos")
public class DestinationController {

    private final IDestinationService iDestinationService;

    @Autowired
    public DestinationController(IDestinationService iDestinationService) {
        this.iDestinationService = iDestinationService;
    }

    @PostMapping("/saveDestination")
    @Transactional
    public ResponseEntity<Object> saveDestination(@ModelAttribute @Valid DestinationDataDto data) {
        try {
            Optional<DestinationModel> destinationModelOptional = this.iDestinationService.findByName(data.getName());
            if (destinationModelOptional.isPresent()) {
                throw new DestinationException(" This data already exists! ");
            }
            DestinationModel destinationModel = new DestinationModel(data);
            DestinationModel destinationModelSaved = this.iDestinationService.save(destinationModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(destinationModelSaved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteDestination/{depositionId}")
    @Transactional
    public ResponseEntity<Object> deleteDestination(@PathVariable(value = "depositionId")UUID depositionId) {
        Optional<DestinationModel> destinationModelOptional = this.iDestinationService.findById(depositionId);
        if (destinationModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Destination not found! ");
        }
        this.iDestinationService.delete(destinationModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Destination deleted successfully! ");
    }

    @GetMapping("/findAllDestinations")
    public ResponseEntity<Page<DestinationModel>> findAllDestinations(@PageableDefault(size = 5, sort = {"name"})
                                                                              Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.iDestinationService.findAll(pageable));
    }

    @PutMapping("/updateDestination")
    @Transactional
    public ResponseEntity<Object> updateDestination(@ModelAttribute @Validated(DestinationDataDto.DestinationView.DestinationPut.class)
                                                    @JsonView(DestinationDataDto.DestinationView.DestinationPut.class) DestinationDataDto data) {
        Optional<DestinationModel> destinationModelOptional = this.iDestinationService.findById(data.getDestinationId());
        if (destinationModelOptional.isEmpty()) {
            throw new DestinationException(" Destino não encontrado! ");
        } else {
            DestinationModel destinationModel = destinationModelOptional.get();
            if (!(data.getPrice() == 0) && !(data.getImage() == null)) {
                destinationModel.setPrice(data.getPrice());
                try {
                    destinationModel.setImage(data.getImage().getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            } else if (!(data.getPrice() == 0)) {
                destinationModel.setPrice(data.getPrice());
            } else if (!(data.getImage() == null)) {
                try {
                    destinationModel.setImage(data.getImage().getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            } else {
                throw new DestinationException("O campo preço ou a imagem deve ser preenchido!");
            }
            this.iDestinationService.save(destinationModel);
            return ResponseEntity.status(HttpStatus.OK).body(destinationModel);
        }
    }

}
