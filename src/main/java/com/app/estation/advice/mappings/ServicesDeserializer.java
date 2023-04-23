package com.app.estation.advice.mappings;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.app.estation.entity.Services;
import com.fasterxml.jackson.databind.JsonNode;

public class ServicesDeserializer extends JsonDeserializer<Services> {
    @Override
    public Services deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Long id = node.get("id").asLong();
        String nom_service = node.get("nom_service").asText();
        String description = node.get("description").asText();
        return new Services(id, nom_service, description);
    }
}

