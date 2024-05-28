package com.jornadamilhas.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepositionsDataDto {

    public interface DepositionsView {
        public static interface DepositionsPut {}
    }

    @NotNull(groups = DepositionsView.DepositionsPut.class)
    @JsonView(DepositionsView.DepositionsPut.class)
    private UUID depositionId;

    @NotNull
    private String name;

    @NotNull
    @JsonView(DepositionsView.DepositionsPut.class)
    private String testimony;

    @JsonView(DepositionsView.DepositionsPut.class)
    private byte[] image;

    public DepositionsDataDto(DepositionsModel depositionsModel) {
        BeanUtils.copyProperties(depositionsModel, this);
    }
}
