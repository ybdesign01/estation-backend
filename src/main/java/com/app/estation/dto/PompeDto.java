package com.app.estation.dto;

import com.app.estation.dto.User.PompeUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PompeDto {

    private Long id_pompe;
    @NotBlank(message = "nom_pompe_mandatory")
    private String nom_pompe;
    private List<CiternePompeDto> citernes;
    private List<PompeUserDto> users;


    public PompeDto() {
    }

    public PompeDto(final Long id_pompe, final String nom_pompe, final List<CiternePompeDto> citernes, final List<PompeUserDto> users) {
        this.id_pompe = id_pompe;
        this.nom_pompe = nom_pompe;
        this.citernes = citernes;
        this.users = users;
    }

    public Long getId_pompe() {
        return this.id_pompe;
    }

    public void setId_pompe(final Long id_pompe) {
        this.id_pompe = id_pompe;
    }

    public String getNom_pompe() {
        return this.nom_pompe;
    }

    public void setNom_pompe(final String nom_pompe) {
        this.nom_pompe = nom_pompe;
    }

    public List<CiternePompeDto> getCiternes() {
        return this.citernes;
    }

    public void setCiternes(final List<CiternePompeDto> citernes) {
        this.citernes = citernes;
    }

    public List<PompeUserDto> getUsers() {
        return this.users;
    }

    public void setUsers(final List<PompeUserDto> users) {
        this.users = users;
    }
}
