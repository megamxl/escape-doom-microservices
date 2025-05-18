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
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "RiddleDTO", description = "Base schema for a riddle")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class RiddleDTO {

    private @Nullable String riddleId;

    private @Nullable String levelId;

    private @Nullable CodingLanguage language;

    private @Nullable String functionSignature;

    private @Nullable String input;

    private @Nullable String variableName;

    private @Nullable String expectedOutput;

    public RiddleDTO riddleId(String riddleId) {
        this.riddleId = riddleId;
        return this;
    }

    /**
     * The unique ID of the riddle
     *
     * @return riddleId
     */

    @Schema(name = "riddle_id", example = "5830daed-cb7f-47dd-8248-5dee9bf0aa3d", description = "The unique ID of the riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("riddle_id")
    public String getRiddleId() {
        return riddleId;
    }

    public void setRiddleId(String riddleId) {
        this.riddleId = riddleId;
    }

    public RiddleDTO levelId(String levelId) {
        this.levelId = levelId;
        return this;
    }

    /**
     * The ID of the escape room level that contains this riddle
     *
     * @return levelId
     */

    @Schema(name = "level_id", example = "a12b34c5-6789-4def-abcd-12345678abcd", description = "The ID of the escape room level that contains this riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level_id")
    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public RiddleDTO language(CodingLanguage language) {
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

    public RiddleDTO functionSignature(String functionSignature) {
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

    public RiddleDTO input(String input) {
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

    public RiddleDTO variableName(String variableName) {
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

    public RiddleDTO expectedOutput(String expectedOutput) {
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
        RiddleDTO riddleDTO = (RiddleDTO) o;
        return Objects.equals(this.riddleId, riddleDTO.riddleId) && Objects.equals(this.levelId, riddleDTO.levelId)
                && Objects.equals(this.language, riddleDTO.language)
                && Objects.equals(this.functionSignature, riddleDTO.functionSignature)
                && Objects.equals(this.input, riddleDTO.input)
                && Objects.equals(this.variableName, riddleDTO.variableName)
                && Objects.equals(this.expectedOutput, riddleDTO.expectedOutput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riddleId, levelId, language, functionSignature, input, variableName, expectedOutput);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RiddleDTO {\n");
        sb.append("    riddleId: ").append(toIndentedString(riddleId)).append("\n");
        sb.append("    levelId: ").append(toIndentedString(levelId)).append("\n");
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
