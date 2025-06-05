package at.escapedoom.data.data.entity.riddle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RiddleType {
    CODING("CODING"), STRING_COMPARE("STRING_COMPARE");

    private final String value;

    RiddleType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static RiddleType fromValue(String value) {
        for (RiddleType type : RiddleType.values()) {
            if (type.value.equalsIgnoreCase(value)) { // Case-insensitive comparison
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown RiddleType value: " + value);
    }

}
