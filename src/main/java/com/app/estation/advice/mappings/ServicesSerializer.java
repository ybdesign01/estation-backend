package com.app.estation.advice.mappings;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.app.estation.entity.Services;
public class ServicesSerializer extends JsonSerializer<Services> {
    @Override
    public void serialize(Services services, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", services.getId());
        jsonGenerator.writeStringField("nom_service", services.getNom_service());
        jsonGenerator.writeStringField("description", services.getDescription());
        jsonGenerator.writeEndObject();
    }
}
