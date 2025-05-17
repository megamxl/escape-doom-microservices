package at.escapedoom.data.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Base schema for any request to the scene API
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "SceneRequestDTO", description = "Base schema for any request to the scene API")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class SceneRequestDTO {

    private @Nullable Integer sceneSequence;

    private @Nullable String levelId;

    private @Nullable String backgroundImageUri;

    private @Nullable String name;

    public SceneRequestDTO sceneSequence(Integer sceneSequence) {
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

    public SceneRequestDTO levelId(String levelId) {
        this.levelId = levelId;
        return this;
    }

    /**
     * The ID of the Escape Room Level this scene belongs to
     *
     * @return levelId
     */

    @Schema(name = "level_id", example = "c2d1a3b4-5e6f-47b8-9c9d-0a1b2c3d4e5f", description = "The ID of the Escape Room Level this scene belongs to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level_id")
    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public SceneRequestDTO backgroundImageUri(String backgroundImageUri) {
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

    public SceneRequestDTO name(String name) {
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
        SceneRequestDTO sceneRequestDTO = (SceneRequestDTO) o;
        return Objects.equals(this.sceneSequence, sceneRequestDTO.sceneSequence)
                && Objects.equals(this.levelId, sceneRequestDTO.levelId)
                && Objects.equals(this.backgroundImageUri, sceneRequestDTO.backgroundImageUri)
                && Objects.equals(this.name, sceneRequestDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sceneSequence, levelId, backgroundImageUri, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SceneRequestDTO {\n");
        sb.append("    sceneSequence: ").append(toIndentedString(sceneSequence)).append("\n");
        sb.append("    levelId: ").append(toIndentedString(levelId)).append("\n");
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
