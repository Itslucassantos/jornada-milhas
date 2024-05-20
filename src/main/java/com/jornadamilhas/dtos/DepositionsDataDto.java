package com.jornadamilhas.dtos;

import com.jornadamilhas.models.DepositionsModel;
import jakarta.validation.constraints.NotNull;
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
public class DepositionsDataDto {

    private UUID depositionId;

    @NotNull
    private String name;

    @NotNull
    private String testimony;

    private String imageUrl;

    public DepositionsDataDto(DepositionsModel depositionsModel) {
        BeanUtils.copyProperties(depositionsModel, this);
    }
}
