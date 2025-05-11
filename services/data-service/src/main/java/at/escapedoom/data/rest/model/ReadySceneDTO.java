package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.NodeDTO;
import at.escapedoom.data.rest.model.SceneDTO;
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
 * ReadySceneDTO
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class ReadySceneDTO {

    private @Nullable SceneDTO scene;

    @lombok.Builder.Default
    @Valid
    private List<@Valid NodeDTO> nodes = new ArrayList<>();

    public ReadySceneDTO scene(SceneDTO scene) {
        this.scene = scene;
        return this;
    }

    /**
     * Get scene
     *
     * @return scene
     */
    @Valid
    @Schema(name = "scene", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("scene")
    public SceneDTO getScene() {
        return scene;
    }

    public void setScene(SceneDTO scene) {
        this.scene = scene;
    }

    public ReadySceneDTO nodes(List<@Valid NodeDTO> nodes) {
        this.nodes = nodes;
        return this;
    }

    public ReadySceneDTO addNodesItem(NodeDTO nodesItem) {
        if (this.nodes == null) {
            this.nodes = new ArrayList<>();
        }
        this.nodes.add(nodesItem);
        return this;
    }

    /**
     * Get nodes
     *
     * @return nodes
     */
    @Valid
    @Schema(name = "nodes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("nodes")
    public List<@Valid NodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<@Valid NodeDTO> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReadySceneDTO readySceneDTO = (ReadySceneDTO) o;
        return Objects.equals(this.scene, readySceneDTO.scene) && Objects.equals(this.nodes, readySceneDTO.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scene, nodes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReadySceneDTO {\n");
        sb.append("    scene: ").append(toIndentedString(scene)).append("\n");
        sb.append("    nodes: ").append(toIndentedString(nodes)).append("\n");
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
