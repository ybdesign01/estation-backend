package com.app.estation.entity;
import jakarta.persistence.*;


import java.util.List;


@Entity
@Table(name = "pompe")
public class Pompe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pompe;
    private String nom_pompe;

    @ManyToMany
    @JoinTable(
            name = "citerne_pompe",
            joinColumns = @JoinColumn(name = "id_pompe"),
            inverseJoinColumns = @JoinColumn(name = "id_citerne"))
    private List<Citerne> citernes;

    @OneToMany(mappedBy = "pompe")
    private List<PompeUser> users;

    public Pompe(Long id_pompe, String nom_pompe, List<Citerne> citernes, List<PompeUser> users) {
        this.users = users;
        this.id_pompe = id_pompe;
        this.nom_pompe = nom_pompe;
        this.citernes = citernes;
    }

    public Pompe() {
    }

    public Long getId_pompe() {
        return id_pompe;
    }

    public void setId_pompe(Long id_pompe) {
        this.id_pompe = id_pompe;
    }

    public String getNom_pompe() {
        return nom_pompe;
    }

    public void setNom_pompe(String nom_pompe) {
        this.nom_pompe = nom_pompe;
    }

    public List<Citerne> getCiternes() {
        return citernes;
    }

    public void setCiternes(List<Citerne> citernes) {
        this.citernes = citernes;
    }

    public List<PompeUser> getUsers() {
        return this.users;
    }

    public void setUsers(final List<PompeUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Pompe{" +
                "id_pompe=" + id_pompe +
                ", nom_pompe='" + nom_pompe + '\'' +
                '}';
    }
}
