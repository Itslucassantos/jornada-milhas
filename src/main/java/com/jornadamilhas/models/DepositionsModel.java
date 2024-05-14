package com.jornadamilhas.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "depositions")
@Entity(name = "DepositionsModel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositionsModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID depositionId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String testimony;

    private String imageUrl;

}
