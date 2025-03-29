package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.NodeInfoDTO;
import at.escapedoom.data.rest.model.NodeType;
import at.escapedoom.data.rest.model.PositionDTO;
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

    private @Nullable NodeType nodeType;

    private @Nullable NodeInfoDTO nodeInfo;

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

    public NodeCreationRequest nodeType(NodeType nodeType) {
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

    public NodeCreationRequest nodeInfo(NodeInfoDTO nodeInfo) {
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
                && Objects.equals(this.nodeType, nodeCreationRequest.nodeType)
                && Objects.equals(this.nodeInfo, nodeCreationRequest.nodeInfo)
                && Objects.equals(this.position, nodeCreationRequest.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sceneId, nodeType, nodeInfo, position);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NodeCreationRequest {\n");
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
