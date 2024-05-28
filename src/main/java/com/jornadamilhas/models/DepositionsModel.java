package com.jornadamilhas.models;

import com.jornadamilhas.dtos.DepositionsDataDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "depositions")
@Entity(name = "DepositionsModel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositionsModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID depositionId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String testimony;

    private byte[] image;

    public DepositionsModel(DepositionsDataDto data) {
        BeanUtils.copyProperties(data, this);
    }

}
