package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.NodeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * A scene of an escape-room instance
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "SceneDTO", description = "A scene of an escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class SceneDTO {

    private @Nullable String escapeRoomLevelId;

    private @Nullable String escapeRoomSequenceId;

    private @Nullable Integer sceneSequence;

    @lombok.Builder.Default
    @Valid
    private List<@Valid NodeDTO> nodes = new ArrayList<>();

    private @Nullable String backgroundImageUri;

    private @Nullable String name;

    public SceneDTO escapeRoomLevelId(String escapeRoomLevelId) {
        this.escapeRoomLevelId = escapeRoomLevelId;
        return this;
    }

    /**
     * The ID of the escape room level that contains this riddle
     *
     * @return escapeRoomLevelId
     */

    @Schema(name = "escape_room_level_id", example = "a12b34c5-6789-4def-abcd-12345678abcd", description = "The ID of the escape room level that contains this riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_level_id")
    public String getEscapeRoomLevelId() {
        return escapeRoomLevelId;
    }

    public void setEscapeRoomLevelId(String escapeRoomLevelId) {
        this.escapeRoomLevelId = escapeRoomLevelId;
    }

    public SceneDTO escapeRoomSequenceId(String escapeRoomSequenceId) {
        this.escapeRoomSequenceId = escapeRoomSequenceId;
        return this;
    }

    /**
     * The unique ID of the scene sequence
     *
     * @return escapeRoomSequenceId
     */

    @Schema(name = "escape_room_sequence_id", example = "241a70fe-47d6-4756-9ac7-330f1b199e84", description = "The unique ID of the scene sequence", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_sequence_id")
    public String getEscapeRoomSequenceId() {
        return escapeRoomSequenceId;
    }

    public void setEscapeRoomSequenceId(String escapeRoomSequenceId) {
        this.escapeRoomSequenceId = escapeRoomSequenceId;
    }

    public SceneDTO sceneSequence(Integer sceneSequence) {
        this.sceneSequence = sceneSequence;
        return this;
    }

    /**
     * Defines the \"position\" of the scene in the escape room
     *
     * @return sceneSequence
     */

    @Schema(name = "scene_sequence", example = "1", description = "Defines the \"position\" of the scene in the escape room", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("scene_sequence")
    public Integer getSceneSequence() {
        return sceneSequence;
    }

    public void setSceneSequence(Integer sceneSequence) {
        this.sceneSequence = sceneSequence;
    }

    public SceneDTO nodes(List<@Valid NodeDTO> nodes) {
        this.nodes = nodes;
        return this;
    }

    public SceneDTO addNodesItem(NodeDTO nodesItem) {
        if (this.nodes == null) {
            this.nodes = new ArrayList<>();
        }
        this.nodes.add(nodesItem);
        return this;
    }

    /**
     * The nodes of the scene
     *
     * @return nodes
     */
    @Valid
    @Schema(name = "nodes", description = "The nodes of the scene", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("nodes")
    public List<@Valid NodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<@Valid NodeDTO> nodes) {
        this.nodes = nodes;
    }

    public SceneDTO backgroundImageUri(String backgroundImageUri) {
        this.backgroundImageUri = backgroundImageUri;
        return this;
    }

    /**
     * The URI of the background image
     *
     * @return backgroundImageUri
     */

    @Schema(name = "background_image_uri", example = "https://example.com/background.png", description = "The URI of the background image", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("background_image_uri")
    public String getBackgroundImageUri() {
        return backgroundImageUri;
    }

    public void setBackgroundImageUri(String backgroundImageUri) {
        this.backgroundImageUri = backgroundImageUri;
    }

    public SceneDTO name(String name) {
        this.name = name;
        return this;
    }

    /**
     * The name of the scene
     *
     * @return name
     */

    @Schema(name = "name", example = "Scene 1", description = "The name of the scene", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SceneDTO sceneDTO = (SceneDTO) o;
        return Objects.equals(this.escapeRoomLevelId, sceneDTO.escapeRoomLevelId)
                && Objects.equals(this.escapeRoomSequenceId, sceneDTO.escapeRoomSequenceId)
                && Objects.equals(this.sceneSequence, sceneDTO.sceneSequence)
                && Objects.equals(this.nodes, sceneDTO.nodes)
                && Objects.equals(this.backgroundImageUri, sceneDTO.backgroundImageUri)
                && Objects.equals(this.name, sceneDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escapeRoomLevelId, escapeRoomSequenceId, sceneSequence, nodes, backgroundImageUri, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SceneDTO {\n");
        sb.append("    escapeRoomLevelId: ").append(toIndentedString(escapeRoomLevelId)).append("\n");
        sb.append("    escapeRoomSequenceId: ").append(toIndentedString(escapeRoomSequenceId)).append("\n");
        sb.append("    sceneSequence: ").append(toIndentedString(sceneSequence)).append("\n");
        sb.append("    nodes: ").append(toIndentedString(nodes)).append("\n");
        sb.append("    backgroundImageUri: ").append(toIndentedString(backgroundImageUri)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
