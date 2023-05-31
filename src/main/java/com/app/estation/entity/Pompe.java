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
    @OneToMany(mappedBy = "pompe")
    private List<PompeUser> users;

    @OneToMany(mappedBy = "pompe")
    private List<CiternePompe> citernes;

    public Pompe(Long id_pompe, String nom_pompe, List<CiternePompe> citernes, List<PompeUser> users) {
        this.users = users;
        this.id_pompe = id_pompe;
        this.nom_pompe = nom_pompe;
        this.citernes = citernes;
    }

    public Pompe() {
    }

    public void setCiternes(final List<CiternePompe> citernes) {
        this.citernes = citernes;
    }

    public List<CiternePompe> getCiternes() {
        return this.citernes;
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
                ", citernes=" + citernes +
                '}';
    }
}
