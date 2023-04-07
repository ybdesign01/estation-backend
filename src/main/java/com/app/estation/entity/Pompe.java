package com.app.estation.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "builder")
@Entity
@Table(name = "pompe")
public class Pompe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pompe;
    private String nom_pompe;

    @ManyToMany
    @JoinTable(
            name = "citerne_pompe",
            joinColumns = @JoinColumn(name = "id_pompe"),
            inverseJoinColumns = @JoinColumn(name = "id_citerne"))
    private List<Citerne> citernes;




}
