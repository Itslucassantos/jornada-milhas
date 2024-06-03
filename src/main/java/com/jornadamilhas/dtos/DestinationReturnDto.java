package com.jornadamilhas.dtos;

import com.jornadamilhas.models.DestinationModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationReturnDto {

    private UUID depositionId;

    private String name;

    private double price;

    private byte[] returnedImage;

    public DestinationReturnDto(DestinationModel destinationModel) {
        BeanUtils.copyProperties(destinationModel, this);
        this.returnedImage = destinationModel.getImage();
    }
}
