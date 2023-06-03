package com.app.estation.dto;

public class DebitRequest {

        private Double montant;
        private Long stationId;

        private String note;

        public String getNote() {
            return this.note;
        }

        public void setNote(final String note) {
            this.note = note;
        }

    public Long getStationId() {
        return this.stationId;
    }

    public void setStationId(final Long stationId) {
        this.stationId = stationId;
    }

    public Double getMontant() {
            return this.montant;
        }

        public void setMontant(final Double montant) {
            this.montant = montant;
        }
}
