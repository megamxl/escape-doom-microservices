package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.CodingLanguage;
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
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor

@Schema(name = "Riddle", description = "Base schema for a riddle")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class Riddle {

    private @Nullable String escapeRoomRiddleId;

    private @Nullable CodingLanguage language;

    private @Nullable String functionSignature;

    private @Nullable String input;

    private @Nullable String variableName;

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

    public Riddle language(CodingLanguage language) {
        this.language = language;
        return this;
    }

    /**
     * Get language
     *
     * @return language
     */
    @Valid
    @Schema(name = "language", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("language")
    public CodingLanguage getLanguage() {
        return language;
    }

    public void setLanguage(CodingLanguage language) {
        this.language = language;
    }

    public Riddle functionSignature(String functionSignature) {
        this.functionSignature = functionSignature;
        return this;
    }

    /**
     * The function signature
     *
     * @return functionSignature
     */

    @Schema(name = "function_signature", example = "public static int sum(int a, int b)", description = "The function signature", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("function_signature")
    public String getFunctionSignature() {
        return functionSignature;
    }

    public void setFunctionSignature(String functionSignature) {
        this.functionSignature = functionSignature;
    }

    public Riddle input(String input) {
        this.input = input;
        return this;
    }

    /**
     * The input values for the function
     *
     * @return input
     */

    @Schema(name = "input", example = "2, 3", description = "The input values for the function", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("input")
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Riddle variableName(String variableName) {
        this.variableName = variableName;
        return this;
    }

    /**
     * The name of the variable to compare
     *
     * @return variableName
     */

    @Schema(name = "variable_name", example = "result", description = "The name of the variable to compare", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("variable_name")
    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
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
                && Objects.equals(this.language, riddle.language)
                && Objects.equals(this.functionSignature, riddle.functionSignature)
                && Objects.equals(this.input, riddle.input) && Objects.equals(this.variableName, riddle.variableName)
                && Objects.equals(this.expectedOutput, riddle.expectedOutput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escapeRoomRiddleId, language, functionSignature, input, variableName, expectedOutput);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Riddle {\n");
        sb.append("    escapeRoomRiddleId: ").append(toIndentedString(escapeRoomRiddleId)).append("\n");
        sb.append("    language: ").append(toIndentedString(language)).append("\n");
        sb.append("    functionSignature: ").append(toIndentedString(functionSignature)).append("\n");
        sb.append("    input: ").append(toIndentedString(input)).append("\n");
        sb.append("    variableName: ").append(toIndentedString(variableName)).append("\n");
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
