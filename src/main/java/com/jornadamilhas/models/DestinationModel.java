package com.jornadamilhas.models;

import com.jornadamilhas.dtos.DestinationDataDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "destinations")
@Entity(name = "DestinationModel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID destinationId;

    @Column
    private UUID destinationDescriptionModelId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private byte[] image;

    public DestinationModel(DestinationDataDto data) {
        BeanUtils.copyProperties(data, this);
        try {
            this.image = data.getImage().getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
