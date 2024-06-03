package com.jornadamilhas.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jornadamilhas.models.DestinationModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DestinationDataDto {

    private UUID depositionId;

    @NotNull
    private String name;

    @NotNull
    private double price;

    @NotNull
    private MultipartFile image;

    private byte[] returnedImage;

    public DestinationDataDto(DestinationModel destinationModel) {
        BeanUtils.copyProperties(destinationModel, this);
        this.returnedImage = destinationModel.getImage();
    }

}
