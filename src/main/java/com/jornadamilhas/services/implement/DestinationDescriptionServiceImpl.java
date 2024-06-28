package com.jornadamilhas.services.implement;

import com.jornadamilhas.dtos.DestinationDescriptionDto;
import com.jornadamilhas.models.DestinationDescriptionModel;
import com.jornadamilhas.models.DestinationModel;
import com.jornadamilhas.repositories.IDestinationDescriptionRepository;
import com.jornadamilhas.repositories.IDestinationRepository;
import com.jornadamilhas.services.IDestinationDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class DestinationDescriptionServiceImpl implements IDestinationDescriptionService {

    private final IDestinationDescriptionRepository iDestinationDescriptionRepository;
    private final IDestinationRepository iDestinationRepository;
    private final ConsultaChatGPT consultaChatGPT;

    @Autowired
    public DestinationDescriptionServiceImpl(IDestinationDescriptionRepository iDestinationDescriptionRepository, IDestinationRepository iDestinationRepository, ConsultaChatGPT consultaChatGPT) {
        this.iDestinationDescriptionRepository = iDestinationDescriptionRepository;
        this.iDestinationRepository = iDestinationRepository;
        this.consultaChatGPT = consultaChatGPT;
    }

    @Override
    public DestinationDescriptionModel save(DestinationDescriptionDto data) {
        DestinationDescriptionModel descriptionModel = null;
        Optional<DestinationModel> destinationModelOptional = this.iDestinationRepository.findById(data.getDestinationId());
        if (data.getDescriptiveText() == null) {
            String text = consultaChatGPT.obterInformacao(destinationModelOptional.get().getName());
            descriptionModel.setDescriptiveText(text);
        }
        try {
            descriptionModel = new DestinationDescriptionModel(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DestinationDescriptionModel descriptionModelSaved = this.iDestinationDescriptionRepository.save(descriptionModel);
        DestinationModel destinationModel = destinationModelOptional.get();
        destinationModel.setDestinationDescriptionModelId(descriptionModelSaved.getDestinationDescriptionId());
        this.iDestinationRepository.save(destinationModel);
        return descriptionModelSaved;
    }

    @Override
    public Optional<DestinationDescriptionModel> findById(UUID destinationDescriptionId) {
        return this.iDestinationDescriptionRepository.findById(destinationDescriptionId);
    }

    @Override
    public void delete(DestinationDescriptionModel descriptionModel) {
        Optional<DestinationModel> destinationModelOptional = this.iDestinationRepository.findById(descriptionModel.getDestinationId());
        DestinationModel destinationModel = destinationModelOptional.get();
        destinationModel.setDestinationDescriptionModelId(null);
        this.iDestinationRepository.save(destinationModel);
        this.iDestinationDescriptionRepository.delete(descriptionModel);
    }

    @Override
    public DestinationDescriptionModel update(DestinationDescriptionDto data) {
        Optional<DestinationDescriptionModel> descriptionModelOptional = this.iDestinationDescriptionRepository
                .findById(data.getDestinationDescriptionId());
        DestinationDescriptionModel descriptionModel = descriptionModelOptional.get();
        if (data.getImage1() != null) {
            try {
                descriptionModel.setImage1(data.getImage1().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } if (data.getImage2() != null) {
            try {
                descriptionModel.setImage2(data.getImage2().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } if (data.getName() != null) {
            descriptionModel.setName(data.getName());
        } if (data.getMeta() != null) {
            descriptionModel.setMeta(data.getMeta());
        } if (data.getDescriptiveText() != null) {
            descriptionModel.setDescriptiveText(data.getDescriptiveText());
        }
        this.iDestinationDescriptionRepository.save(descriptionModel);
        return descriptionModel;
    }

}
