package at.escapedoom.data.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Base schema for riddle creation
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "RiddleCreationRequestDTO", description = "Base schema for riddle creation")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class RiddleCreationRequestDTO {

    private @Nullable CodingLanguage language;

    private @Nullable String functionSignature;

    private @Nullable String input;

    private @Nullable String variableName;

    private @Nullable String expectedOutput;

    private @Nullable String levelId;

    public RiddleCreationRequestDTO language(CodingLanguage language) {
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

    public RiddleCreationRequestDTO functionSignature(String functionSignature) {
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

    public RiddleCreationRequestDTO input(String input) {
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

    public RiddleCreationRequestDTO variableName(String variableName) {
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

    public RiddleCreationRequestDTO expectedOutput(String expectedOutput) {
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

    public RiddleCreationRequestDTO levelId(String levelId) {
        this.levelId = levelId;
        return this;
    }

    /**
     * The ID of the Escape Room Level this riddle belongs to
     *
     * @return levelId
     */

    @Schema(name = "level_id", example = "c2d1a3b4-5e6f-47b8-9c9d-0a1b2c3d4e5f", description = "The ID of the Escape Room Level this riddle belongs to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level_id")
    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RiddleCreationRequestDTO riddleCreationRequestDTO = (RiddleCreationRequestDTO) o;
        return Objects.equals(this.language, riddleCreationRequestDTO.language)
                && Objects.equals(this.functionSignature, riddleCreationRequestDTO.functionSignature)
                && Objects.equals(this.input, riddleCreationRequestDTO.input)
                && Objects.equals(this.variableName, riddleCreationRequestDTO.variableName)
                && Objects.equals(this.expectedOutput, riddleCreationRequestDTO.expectedOutput)
                && Objects.equals(this.levelId, riddleCreationRequestDTO.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, functionSignature, input, variableName, expectedOutput, levelId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RiddleCreationRequestDTO {\n");
        sb.append("    language: ").append(toIndentedString(language)).append("\n");
        sb.append("    functionSignature: ").append(toIndentedString(functionSignature)).append("\n");
        sb.append("    input: ").append(toIndentedString(input)).append("\n");
        sb.append("    variableName: ").append(toIndentedString(variableName)).append("\n");
        sb.append("    expectedOutput: ").append(toIndentedString(expectedOutput)).append("\n");
        sb.append("    levelId: ").append(toIndentedString(levelId)).append("\n");
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
