package com.app.estation.advice;

import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class StationDeserializer extends JsonDeserializer<Station> {

    @Override
    public Station deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Long id = node.get("id").asLong();
        String name = node.get("nom_station").asText();
        String address = node.get("adresse").asText();

        Set<Services> services = new HashSet<>();
        JsonNode servicesNode = node.get("services");
        if (servicesNode.isArray()) {
            for (JsonNode serviceNode : servicesNode) {
                Long serviceId = serviceNode.get("id").asLong();
                String serviceName = serviceNode.get("nom_service").asText();
                String serviceDescription = serviceNode.get("description").asText();
                Services service = new Services(serviceId, serviceName, serviceDescription);
                services.add(service);
            }
        } else {
            Long serviceId = servicesNode.get("id").asLong();
            String serviceName = servicesNode.get("nom_service").asText();
            String serviceDescription = servicesNode.get("description").asText();
            Services service = new Services(serviceId, serviceName, serviceDescription);
            services.add(service);
        }

        Station station = new Station();
        station.setId(id);
        station.setNom_station(name);
        station.setAdresse(address);
        station.setServices(services);

        return station;
    }
}
