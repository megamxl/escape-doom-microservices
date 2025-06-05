package at.escapedoom.data.utils;

import at.escapedoom.data.data.entity.riddle.TestCaseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = false) // Apply manually where needed
public class TestCaseListConverter implements AttributeConverter<List<TestCaseEntity>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<TestCaseEntity> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "[]";
        }
        try {
            // ObjectMapper will use its default serialization for TestCase objects,
            // which includes handling their internal Map<String, String> fields.
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting List<TestCase> to JSON string", e);
        }
    }

    @Override
    public List<TestCaseEntity> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty() || "null".equalsIgnoreCase(dbData)) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<TestCaseEntity>>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON string to List<TestCase>", e);
        }
    }
}