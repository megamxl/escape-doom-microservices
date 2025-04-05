package at.escapedoom.player.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.player.rest.model.NodeDTO;
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

    private @Nullable String sceneId;

    private @Nullable Integer sceneSequence;

    private @Nullable String levelId;

    @lombok.Builder.Default
    @Valid
    private List<@Valid NodeDTO> nodes = new ArrayList<>();

    private @Nullable String backgroundImageUri;

    private @Nullable String name;

    public SceneDTO sceneId(String sceneId) {
        this.sceneId = sceneId;
        return this;
    }

    /**
     * The unique ID of the scene
     *
     * @return sceneId
     */

    @Schema(name = "scene_id", example = "241a70fe-47d6-4756-9ac7-330f1b199e84", description = "The unique ID of the scene", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("scene_id")
    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
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

    public SceneDTO levelId(String levelId) {
        this.levelId = levelId;
        return this;
    }

    /**
     * The ID of the escape room level that contains this riddle
     *
     * @return levelId
     */

    @Schema(name = "level_id", example = "a12b34c5-6789-4def-abcd-12345678abcd", description = "The ID of the escape room level that contains this riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level_id")
    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
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
        return Objects.equals(this.sceneId, sceneDTO.sceneId)
                && Objects.equals(this.sceneSequence, sceneDTO.sceneSequence)
                && Objects.equals(this.levelId, sceneDTO.levelId) && Objects.equals(this.nodes, sceneDTO.nodes)
                && Objects.equals(this.backgroundImageUri, sceneDTO.backgroundImageUri)
                && Objects.equals(this.name, sceneDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sceneId, sceneSequence, levelId, nodes, backgroundImageUri, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SceneDTO {\n");
        sb.append("    sceneId: ").append(toIndentedString(sceneId)).append("\n");
        sb.append("    sceneSequence: ").append(toIndentedString(sceneSequence)).append("\n");
        sb.append("    levelId: ").append(toIndentedString(levelId)).append("\n");
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
