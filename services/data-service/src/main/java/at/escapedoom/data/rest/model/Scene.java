package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.Node;
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

@Schema(name = "Scene", description = "A scene of an escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class Scene {

    private @Nullable String escapeRoomSequenceId;

    @Valid
    private List<@Valid Node> nodes = new ArrayList<>();

    private @Nullable String backgroundImageUri;

    private @Nullable String name;

    public Scene escapeRoomSequenceId(String escapeRoomSequenceId) {
        this.escapeRoomSequenceId = escapeRoomSequenceId;
        return this;
    }

    /**
     * The unique ID of the scene sequence
     *
     * @return escapeRoomSequenceId
     */

    @Schema(name = "escape_room_sequence_id", example = "1", description = "The unique ID of the scene sequence", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_sequence_id")
    public String getEscapeRoomSequenceId() {
        return escapeRoomSequenceId;
    }

    public void setEscapeRoomSequenceId(String escapeRoomSequenceId) {
        this.escapeRoomSequenceId = escapeRoomSequenceId;
    }

    public Scene nodes(List<@Valid Node> nodes) {
        this.nodes = nodes;
        return this;
    }

    public Scene addNodesItem(Node nodesItem) {
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
    public List<@Valid Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<@Valid Node> nodes) {
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
        return Objects.equals(this.escapeRoomSequenceId, scene.escapeRoomSequenceId)
                && Objects.equals(this.nodes, scene.nodes)
                && Objects.equals(this.backgroundImageUri, scene.backgroundImageUri)
                && Objects.equals(this.name, scene.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escapeRoomSequenceId, nodes, backgroundImageUri, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Scene {\n");
        sb.append("    escapeRoomSequenceId: ").append(toIndentedString(escapeRoomSequenceId)).append("\n");
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
