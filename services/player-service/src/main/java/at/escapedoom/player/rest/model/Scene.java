package at.escapedoom.player.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.player.rest.model.NodeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
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

@Schema(name = "Scene", description = "A scene of an escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class Scene {

    private @Nullable BigDecimal sceneSequence;

    @lombok.Builder.Default
    @Valid
    private List<@Valid NodeDTO> nodes = new ArrayList<>();

    private @Nullable String backgroundImageUri;

    private @Nullable String name;

    public Scene sceneSequence(BigDecimal sceneSequence) {
        this.sceneSequence = sceneSequence;
        return this;
    }

    /**
     * The sequence number of the scene
     *
     * @return sceneSequence
     */
    @Valid
    @Schema(name = "scene_sequence", example = "1", description = "The sequence number of the scene", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("scene_sequence")
    public BigDecimal getSceneSequence() {
        return sceneSequence;
    }

    public void setSceneSequence(BigDecimal sceneSequence) {
        this.sceneSequence = sceneSequence;
    }

    public Scene nodes(List<@Valid NodeDTO> nodes) {
        this.nodes = nodes;
        return this;
    }

    public Scene addNodesItem(NodeDTO nodesItem) {
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

    public Scene backgroundImageUri(String backgroundImageUri) {
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

    public Scene name(String name) {
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
        Scene scene = (Scene) o;
        return Objects.equals(this.sceneSequence, scene.sceneSequence) && Objects.equals(this.nodes, scene.nodes)
                && Objects.equals(this.backgroundImageUri, scene.backgroundImageUri)
                && Objects.equals(this.name, scene.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sceneSequence, nodes, backgroundImageUri, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Scene {\n");
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
