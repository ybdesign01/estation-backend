package com.app.estation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Citerne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_citerne;
    private String nom_citerne;
    private String capacite;
    private Long id_produit;

    @ManyToMany
    @JoinTable(
            name = "citerne_pompe",
            joinColumns = @JoinColumn(name = "id_citerne"),
            inverseJoinColumns = @JoinColumn(name = "id_pompe"))
    private List<Pompe> pompes;




}
