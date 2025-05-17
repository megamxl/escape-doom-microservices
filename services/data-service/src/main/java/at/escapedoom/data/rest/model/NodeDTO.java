package at.escapedoom.data.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * A node of an escape-room instance
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "NodeDTO", description = "A node of an escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class NodeDTO {

    private @Nullable UUID nodeId;

    private @Nullable String description;

    private @Nullable String title;

    private @Nullable UUID sceneId;

    private @Nullable NodeSpecificsDTO nodeSpecifics;

    private @Nullable PositionDTO position;

    public NodeDTO nodeId(UUID nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    /**
     * The unique identifier of the node
     *
     * @return nodeId
     */
    @Valid
    @Schema(name = "node_id", description = "The unique identifier of the node", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("node_id")
    public UUID getNodeId() {
        return nodeId;
    }

    public void setNodeId(UUID nodeId) {
        this.nodeId = nodeId;
    }

    public NodeDTO description(String description) {
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

    public NodeDTO title(String title) {
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

    public NodeDTO sceneId(UUID sceneId) {
        this.sceneId = sceneId;
        return this;
    }

    /**
     * The unique identifier of the scene the node belongs to
     *
     * @return sceneId
     */
    @Valid
    @Schema(name = "scene_id", description = "The unique identifier of the scene the node belongs to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("scene_id")
    public UUID getSceneId() {
        return sceneId;
    }

    public void setSceneId(UUID sceneId) {
        this.sceneId = sceneId;
    }

    public NodeDTO nodeSpecifics(NodeSpecificsDTO nodeSpecifics) {
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

    public NodeDTO position(PositionDTO position) {
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
        NodeDTO nodeDTO = (NodeDTO) o;
        return Objects.equals(this.nodeId, nodeDTO.nodeId) && Objects.equals(this.description, nodeDTO.description)
                && Objects.equals(this.title, nodeDTO.title) && Objects.equals(this.sceneId, nodeDTO.sceneId)
                && Objects.equals(this.nodeSpecifics, nodeDTO.nodeSpecifics)
                && Objects.equals(this.position, nodeDTO.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, description, title, sceneId, nodeSpecifics, position);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NodeDTO {\n");
        sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    sceneId: ").append(toIndentedString(sceneId)).append("\n");
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
