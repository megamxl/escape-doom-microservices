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
 * Detail node specifics - Used for the frontend type-safety
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "DetailsNodeSpecificsDTO", description = "Detail node specifics - Used for the frontend type-safety")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class DetailsNodeSpecificsDTO {

    private @Nullable String imageUri;

    private @Nullable NodeType nodeType;

    public DetailsNodeSpecificsDTO imageUri(String imageUri) {
        this.imageUri = imageUri;
        return this;
    }

    /**
     * A Web-URL to the Image
     *
     * @return imageUri
     */

    @Schema(name = "image_uri", example = "https://example.com/background.png", description = "A Web-URL to the Image", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("image_uri")
    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public DetailsNodeSpecificsDTO nodeType(NodeType nodeType) {
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
        DetailsNodeSpecificsDTO detailsNodeSpecificsDTO = (DetailsNodeSpecificsDTO) o;
        return Objects.equals(this.imageUri, detailsNodeSpecificsDTO.imageUri)
                && Objects.equals(this.nodeType, detailsNodeSpecificsDTO.nodeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUri, nodeType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DetailsNodeSpecificsDTO {\n");
        sb.append("    imageUri: ").append(toIndentedString(imageUri)).append("\n");
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
