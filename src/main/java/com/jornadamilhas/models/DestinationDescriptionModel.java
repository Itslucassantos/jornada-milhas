package com.jornadamilhas.models;

import com.jornadamilhas.dtos.DestinationDescriptionDto;
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

@Table(name = "destination_description")
@Entity(name = "DestinationDescriptionModel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDescriptionModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID destinationDescriptionId;

    @Column(nullable = false)
    private UUID destinationId;

    @Column(nullable = false)
    private byte[] image1;

    @Column(nullable = false)
    private byte[] image2;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 160)
    private String meta;

    @Column(nullable = true, length = 1000)
    private String descriptiveText;

    public DestinationDescriptionModel(DestinationDescriptionDto data) throws IOException {
        BeanUtils.copyProperties(data, this);
        this.destinationId = data.getDestinationId();
        this.image1 = data.getImage1().getBytes();
        this.image2 = data.getImage2().getBytes();
    }

}
