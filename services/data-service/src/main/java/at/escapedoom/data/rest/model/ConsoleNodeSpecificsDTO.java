package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.NodeType;
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
 * Console node specifics - Used for the frontend type-safety
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "ConsoleNodeSpecificsDTO", description = "Console node specifics - Used for the frontend type-safety")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class ConsoleNodeSpecificsDTO {

    private @Nullable String returnDescription;

    private @Nullable String constraints;

    private @Nullable String example;

    private @Nullable NodeType nodeType;

    public ConsoleNodeSpecificsDTO returnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
        return this;
    }

    /**
     * Description about the input properties
     *
     * @return returnDescription
     */

    @Schema(name = "return_description", example = "moves: The list of moves available", description = "Description about the input properties", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("return_description")
    public String getReturnDescription() {
        return returnDescription;
    }

    public void setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
    }

    public ConsoleNodeSpecificsDTO constraints(String constraints) {
        this.constraints = constraints;
        return this;
    }

    /**
     * Any code-details helpful for the user
     *
     * @return constraints
     */

    @Schema(name = "constraints", example = "moves[] = UP | DOWN", description = "Any code-details helpful for the user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("constraints")
    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public ConsoleNodeSpecificsDTO example(String example) {
        this.example = example;
        return this;
    }

    /**
     * A code-example in somewhat human-readable form
     *
     * @return example
     */

    @Schema(name = "example", example = "[ \"UP\", \"DOWN\", \"UP\" ]", description = "A code-example in somewhat human-readable form", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("example")
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public ConsoleNodeSpecificsDTO nodeType(NodeType nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    /**
     * Get nodeType
     *
     * @return nodeType
     */
    @Valid
    @Schema(name = "node_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("node_type")
    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsoleNodeSpecificsDTO consoleNodeSpecificsDTO = (ConsoleNodeSpecificsDTO) o;
        return Objects.equals(this.returnDescription, consoleNodeSpecificsDTO.returnDescription)
                && Objects.equals(this.constraints, consoleNodeSpecificsDTO.constraints)
                && Objects.equals(this.example, consoleNodeSpecificsDTO.example)
                && Objects.equals(this.nodeType, consoleNodeSpecificsDTO.nodeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(returnDescription, constraints, example, nodeType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConsoleNodeSpecificsDTO {\n");
        sb.append("    returnDescription: ").append(toIndentedString(returnDescription)).append("\n");
        sb.append("    constraints: ").append(toIndentedString(constraints)).append("\n");
        sb.append("    example: ").append(toIndentedString(example)).append("\n");
        sb.append("    nodeType: ").append(toIndentedString(nodeType)).append("\n");
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
