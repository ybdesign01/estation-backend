package com.app.estation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class EncaissementRequest {

    @NotNull(message = "id_mandatory")
    private Long idPompeUser;

    private String note;

    @Valid
    private List<EncaissementDto> encaissements;

    public EncaissementRequest() {
    }

    public EncaissementRequest(final Long idPompeUser, final Long idProduit, final String note, final List<EncaissementDto> encaissements) {
        this.idPompeUser = idPompeUser;
        this.note = note;
        this.encaissements = encaissements;
    }

    public Long getIdPompeUser() {
        return this.idPompeUser;
    }

    public void setIdPompeUser(final Long idPompeUser) {
        this.idPompeUser = idPompeUser;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public List<EncaissementDto> getEncaissements() {
        return this.encaissements;
    }

    public void setEncaissements(final List<EncaissementDto> encaissements) {
        this.encaissements = encaissements;
    }
}
