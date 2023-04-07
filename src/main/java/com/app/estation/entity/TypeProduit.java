package com.app.estation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TypeProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_type;

    private String nom_type;

}
