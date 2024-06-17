package com.jornadamilhas.dtos;

import com.jornadamilhas.models.DestinationModel;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDescriptionDto {

    private UUID destinationDescriptionId;

    @Column(nullable = false)
    private UUID destinationId;

    @Column(nullable = false)
    private MultipartFile image1;

    @Column(nullable = false)
    private MultipartFile image2;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 160)
    private String meta;

    @Column(nullable = true)
    private String descriptiveText;

}
