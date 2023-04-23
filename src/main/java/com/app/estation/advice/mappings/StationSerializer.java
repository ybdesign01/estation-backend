package com.app.estation.advice.mappings;

import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class StationSerializer extends JsonSerializer<Station> {

    @Override
    public void serialize(Station station, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", station.getId());
        jsonGenerator.writeStringField("nom_station", station.getNom_station());
        jsonGenerator.writeStringField("adresse", station.getAdresse());

        Set<Services> services = station.getServices();
        if (services != null && !services.isEmpty()) {
            jsonGenerator.writeArrayFieldStart("services");
            for (Services service : services) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", service.getId());
                jsonGenerator.writeStringField("nom_service", service.getNom_service());
                jsonGenerator.writeStringField("description", service.getDescription());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }
}