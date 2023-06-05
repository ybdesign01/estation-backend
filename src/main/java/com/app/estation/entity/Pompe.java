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
    private Long compteurInitial;
    @OneToMany(mappedBy = "pompe")
    private List<PompeUser> users;

    @OneToMany(mappedBy = "pompe")
    private List<CiternePompe> citernes;

    public Pompe(final Long id_pompe, final String nom_pompe, final Long compteurInitiale, final List<PompeUser> users, final List<CiternePompe> citernes) {
        this.id_pompe = id_pompe;
        this.nom_pompe = nom_pompe;
        this.compteurInitial = compteurInitiale;
        this.users = users;
        this.citernes = citernes;
    }

    public Long getCompteurInitial() {
        return this.compteurInitial;
    }

    public void setCompteurInitial(final Long compteurInitiale) {
        this.compteurInitial = compteurInitiale;
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
