package at.escapedoom.data.utils;

import at.escapedoom.data.rest.model.CodingLanguage;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

// Mark with @Converter and optionally autoApply = true if you want it applied globally
// to all Map<CodingLanguage, String> fields. Otherwise, apply with @Convert manually.
@Converter(autoApply = false) // Set to 'true' if you want it applied to all Map<CodingLanguage, String>
public class CodingLanguageMapConverter implements AttributeConverter<Map<CodingLanguage, String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<CodingLanguage, String> entityAttribute) {
        if (entityAttribute == null || entityAttribute.isEmpty()) {
            return "{}"; // Store as empty JSON object if null or empty map
        }
        try {
            return objectMapper.writeValueAsString(entityAttribute);
        } catch (JsonProcessingException e) {
            // Log the error and/or throw a runtime exception
            throw new IllegalArgumentException("Error converting Map<CodingLanguage, String> to JSON string", e);
        }
    }

    @Override
    public Map<CodingLanguage, String> convertToEntityAttribute(String databaseColumn) {
        if (databaseColumn == null || databaseColumn.trim().isEmpty() || "null".equalsIgnoreCase(databaseColumn)) {
            return new HashMap<>();
        }
        try {
            // Use TypeReference for deserializing generic types
            return objectMapper.readValue(databaseColumn, new TypeReference<Map<CodingLanguage, String>>() {
            });
        } catch (JsonProcessingException e) {
            // Log the error and/or throw a runtime exception
            throw new IllegalArgumentException("Error converting JSON string to Map<CodingLanguage, String>", e);
        }
    }
}