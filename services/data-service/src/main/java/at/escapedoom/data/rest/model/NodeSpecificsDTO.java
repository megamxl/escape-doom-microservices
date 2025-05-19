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

import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * Depending on the node type, different params should be given
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "NodeSpecificsDTO", description = "Depending on the node type, different params should be given")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class NodeSpecificsDTO {

    private @Nullable NodeType nodeType;

    public NodeSpecificsDTO nodeType(NodeType nodeType) {
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

    /**
     * A container for additional, undeclared properties. This is a holder for any undeclared properties as specified
     * with the 'additionalProperties' keyword in the OAS document.
     */
    private Map<String, Object> additionalProperties;

    /**
     * Set the additional (undeclared) property with the specified name and value. If the property does not already
     * exist, create it otherwise replace it.
     */
    @JsonAnySetter
    public NodeSpecificsDTO putAdditionalProperty(String key, Object value) {
        if (this.additionalProperties == null) {
            this.additionalProperties = new HashMap<String, Object>();
        }
        this.additionalProperties.put(key, value);
        return this;
    }

    /**
     * Return the additional (undeclared) property.
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    /**
     * Return the additional (undeclared) property with the specified name.
     */
    public Object getAdditionalProperty(String key) {
        if (this.additionalProperties == null) {
            return null;
        }
        return this.additionalProperties.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeSpecificsDTO nodeSpecificsDTO = (NodeSpecificsDTO) o;
        return Objects.equals(this.nodeType, nodeSpecificsDTO.nodeType)
                && Objects.equals(this.additionalProperties, nodeSpecificsDTO.additionalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeType, additionalProperties);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NodeSpecificsDTO {\n");
        sb.append("    nodeType: ").append(toIndentedString(nodeType)).append("\n");

        sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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
