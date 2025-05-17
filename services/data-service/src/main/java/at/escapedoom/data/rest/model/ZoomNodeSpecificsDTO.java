package at.escapedoom.data.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Detail node specifics - Used for the frontend type-safety
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "ZoomNodeSpecificsDTO", description = "Detail node specifics - Used for the frontend type-safety")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class ZoomNodeSpecificsDTO {

    private @Nullable UUID linkedSceneId;

    private @Nullable UUID parentSceneId;

    private @Nullable NodeType nodeType;

    public ZoomNodeSpecificsDTO linkedSceneId(UUID linkedSceneId) {
        this.linkedSceneId = linkedSceneId;
        return this;
    }

    /**
     * The scene_id of the scene the node links to
     *
     * @return linkedSceneId
     */
    @Valid
    @Schema(name = "linked_scene_id", example = "241a70fe-47d6-4756-9ac7-330f1b199e84", description = "The scene_id of the scene the node links to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("linked_scene_id")
    public UUID getLinkedSceneId() {
        return linkedSceneId;
    }

    public void setLinkedSceneId(UUID linkedSceneId) {
        this.linkedSceneId = linkedSceneId;
    }

    public ZoomNodeSpecificsDTO parentSceneId(UUID parentSceneId) {
        this.parentSceneId = parentSceneId;
        return this;
    }

    /**
     * The scene_id of the parent it should return to
     *
     * @return parentSceneId
     */
    @Valid
    @Schema(name = "parent_scene_id", example = "241a70fe-47d6-4756-9ac7-330f1b199e84", description = "The scene_id of the parent it should return to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("parent_scene_id")
    public UUID getParentSceneId() {
        return parentSceneId;
    }

    public void setParentSceneId(UUID parentSceneId) {
        this.parentSceneId = parentSceneId;
    }

    public ZoomNodeSpecificsDTO nodeType(NodeType nodeType) {
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
        ZoomNodeSpecificsDTO zoomNodeSpecificsDTO = (ZoomNodeSpecificsDTO) o;
        return Objects.equals(this.linkedSceneId, zoomNodeSpecificsDTO.linkedSceneId)
                && Objects.equals(this.parentSceneId, zoomNodeSpecificsDTO.parentSceneId)
                && Objects.equals(this.nodeType, zoomNodeSpecificsDTO.nodeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkedSceneId, parentSceneId, nodeType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ZoomNodeSpecificsDTO {\n");
        sb.append("    linkedSceneId: ").append(toIndentedString(linkedSceneId)).append("\n");
        sb.append("    parentSceneId: ").append(toIndentedString(parentSceneId)).append("\n");
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
