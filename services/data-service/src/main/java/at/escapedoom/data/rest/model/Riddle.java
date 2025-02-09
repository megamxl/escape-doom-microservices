package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * Base schema for a riddle
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "Riddle", description = "Base schema for a riddle")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class Riddle {

    private @Nullable String escapeRoomRiddleId;

    /**
     * The type of the riddle
     */
    public enum TypeEnum {
        INPUT_STRING_COMPARE_RIDDLE("InputStringCompareRiddle"),

        CODING_RIDDLE("CodingRiddle");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static TypeEnum fromValue(String value) {
            for (TypeEnum b : TypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private @Nullable TypeEnum type;

    private @Nullable String expectedOutput;

    public Riddle escapeRoomRiddleId(String escapeRoomRiddleId) {
        this.escapeRoomRiddleId = escapeRoomRiddleId;
        return this;
    }

    /**
     * The unique ID of the riddle
     *
     * @return escapeRoomRiddleId
     */

    @Schema(name = "escape_room_riddle_id", example = "5830daed-cb7f-47dd-8248-5dee9bf0aa3d", description = "The unique ID of the riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_riddle_id")
    public String getEscapeRoomRiddleId() {
        return escapeRoomRiddleId;
    }

    public void setEscapeRoomRiddleId(String escapeRoomRiddleId) {
        this.escapeRoomRiddleId = escapeRoomRiddleId;
    }

    public Riddle type(TypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * The type of the riddle
     *
     * @return type
     */

    @Schema(name = "type", description = "The type of the riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("type")
    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Riddle expectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
        return this;
    }

    /**
     * The expected output of the riddle
     *
     * @return expectedOutput
     */

    @Schema(name = "expected_output", example = "42", description = "The expected output of the riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("expected_output")
    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Riddle riddle = (Riddle) o;
        return Objects.equals(this.escapeRoomRiddleId, riddle.escapeRoomRiddleId)
                && Objects.equals(this.type, riddle.type) && Objects.equals(this.expectedOutput, riddle.expectedOutput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escapeRoomRiddleId, type, expectedOutput);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Riddle {\n");
        sb.append("    escapeRoomRiddleId: ").append(toIndentedString(escapeRoomRiddleId)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    expectedOutput: ").append(toIndentedString(expectedOutput)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
