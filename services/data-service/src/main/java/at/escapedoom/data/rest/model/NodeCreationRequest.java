package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.NodeSpecificsDTO;
import at.escapedoom.data.rest.model.PositionDTO;
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
 * The payload for creating a node
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "NodeCreationRequest", description = "The payload for creating a node")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class NodeCreationRequest {

    private @Nullable String sceneId;

    private @Nullable String description;

    private @Nullable String title;

    private @Nullable NodeSpecificsDTO nodeSpecifics;

    private @Nullable PositionDTO position;

    public NodeCreationRequest sceneId(String sceneId) {
        this.sceneId = sceneId;
        return this;
    }

    /**
     * The unique ID of the scene the node belongs to
     *
     * @return sceneId
     */

    @Schema(name = "scene_id", example = "241a70fe-47d6-4756-9ac7-330f1b199e84", description = "The unique ID of the scene the node belongs to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("scene_id")
    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public NodeCreationRequest description(String description) {
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

    public NodeCreationRequest title(String title) {
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

    public NodeCreationRequest nodeSpecifics(NodeSpecificsDTO nodeSpecifics) {
        this.nodeSpecifics = nodeSpecifics;
        return this;
    }

    /**
     * Get nodeSpecifics
     *
     * @return nodeSpecifics
     */
    @Valid
    @Schema(name = "node_specifics", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("node_specifics")
    public NodeSpecificsDTO getNodeSpecifics() {
        return nodeSpecifics;
    }

    public void setNodeSpecifics(NodeSpecificsDTO nodeSpecifics) {
        this.nodeSpecifics = nodeSpecifics;
    }

    public NodeCreationRequest position(PositionDTO position) {
        this.position = position;
        return this;
    }

    /**
     * Get position
     *
     * @return position
     */
    @Valid
    @Schema(name = "position", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("position")
    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeCreationRequest nodeCreationRequest = (NodeCreationRequest) o;
        return Objects.equals(this.sceneId, nodeCreationRequest.sceneId)
                && Objects.equals(this.description, nodeCreationRequest.description)
                && Objects.equals(this.title, nodeCreationRequest.title)
                && Objects.equals(this.nodeSpecifics, nodeCreationRequest.nodeSpecifics)
                && Objects.equals(this.position, nodeCreationRequest.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sceneId, description, title, nodeSpecifics, position);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NodeCreationRequest {\n");
        sb.append("    sceneId: ").append(toIndentedString(sceneId)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    nodeSpecifics: ").append(toIndentedString(nodeSpecifics)).append("\n");
        sb.append("    position: ").append(toIndentedString(position)).append("\n");
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
