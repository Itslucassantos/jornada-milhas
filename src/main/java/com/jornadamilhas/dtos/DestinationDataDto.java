package com.jornadamilhas.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDataDto {

    private UUID depositionId;

    @NotNull
    private String name;

    @NotNull
    private double price;

    @NotNull
    private byte[] image;

}
