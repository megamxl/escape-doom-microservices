package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.NodeInfoDTO;
import at.escapedoom.data.rest.model.NodeType;
import at.escapedoom.data.rest.model.PositionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

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

    private @Nullable UUID sceneId;

    private @Nullable NodeType nodeType;

    private @Nullable NodeInfoDTO nodeInfo;

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

    public NodeDTO nodeType(NodeType nodeType) {
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

    public NodeDTO nodeInfo(NodeInfoDTO nodeInfo) {
        this.nodeInfo = nodeInfo;
        return this;
    }

    /**
     * Get nodeInfo
     *
     * @return nodeInfo
     */
    @Valid
    @Schema(name = "node_info", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("node_info")
    public NodeInfoDTO getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(NodeInfoDTO nodeInfo) {
        this.nodeInfo = nodeInfo;
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
        return Objects.equals(this.nodeId, nodeDTO.nodeId) && Objects.equals(this.sceneId, nodeDTO.sceneId)
                && Objects.equals(this.nodeType, nodeDTO.nodeType) && Objects.equals(this.nodeInfo, nodeDTO.nodeInfo)
                && Objects.equals(this.position, nodeDTO.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, sceneId, nodeType, nodeInfo, position);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NodeDTO {\n");
        sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
        sb.append("    sceneId: ").append(toIndentedString(sceneId)).append("\n");
        sb.append("    nodeType: ").append(toIndentedString(nodeType)).append("\n");
        sb.append("    nodeInfo: ").append(toIndentedString(nodeInfo)).append("\n");
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
