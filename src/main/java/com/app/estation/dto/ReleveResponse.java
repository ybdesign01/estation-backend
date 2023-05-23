package com.app.estation.dto;

public class ReleveResponse {

    boolean releve_entree;
    boolean releve_sortie;


    public ReleveResponse() {
    }

    public ReleveResponse(boolean releve_entree, boolean releve_sortie) {
        this.releve_entree = releve_entree;
        this.releve_sortie = releve_sortie;
    }

    public boolean isReleve_entree() {
        return releve_entree;
    }

    public void setReleve_entree(boolean releve_entree) {
        this.releve_entree = releve_entree;
    }


    public boolean isReleve_sortie() {
        return releve_sortie;
    }


    public void setReleve_sortie(boolean releve_sortie) {
        this.releve_sortie = releve_sortie;
    }
}
