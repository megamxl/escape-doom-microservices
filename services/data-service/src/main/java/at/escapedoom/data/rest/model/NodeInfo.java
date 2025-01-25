package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * The information of a node
 */

@Schema(name = "NodeInfo", description = "The information of a node")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class NodeInfo {

    private @Nullable String description;

    private @Nullable String title;

    private @Nullable String imageURI;

    public NodeInfo description(String description) {
        this.description = description;
        return this;
    }

    /**
     * The description of the node
     *
     * @return description
     */

    @Schema(name = "description", example = "This is a story node", description = "The description of the node", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NodeInfo title(String title) {
        this.title = title;
        return this;
    }

    /**
     * The display title of the node
     *
     * @return title
     */

    @Schema(name = "title", example = "I like cheese", description = "The display title of the node", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NodeInfo imageURI(String imageURI) {
        this.imageURI = imageURI;
        return this;
    }

    /**
     * The URI of the image to display
     *
     * @return imageURI
     */

    @Schema(name = "imageURI", example = "https://example.com/image.png", description = "The URI of the image to display", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("imageURI")
    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeInfo nodeInfo = (NodeInfo) o;
        return Objects.equals(this.description, nodeInfo.description) && Objects.equals(this.title, nodeInfo.title)
                && Objects.equals(this.imageURI, nodeInfo.imageURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, title, imageURI);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NodeInfo {\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    imageURI: ").append(toIndentedString(imageURI)).append("\n");
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
