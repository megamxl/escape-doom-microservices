package at.escapedoom.player.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.player.rest.model.RiddleDTO;
import at.escapedoom.player.rest.model.SceneDTO;
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
 * The current level of an escape-room instance
 */
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.Setter

@Schema(name = "LevelDTO", description = "The current level of an escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class LevelDTO {

    private @Nullable String templateId;

    private @Nullable String levelId;

    private @Nullable Integer levelSequence;

    @lombok.Builder.Default
    @Valid
    private List<@Valid SceneDTO> scenes = new ArrayList<>();

    private @Nullable RiddleDTO riddle;

    public LevelDTO templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * The unique ID of the escape room template
     *
     * @return templateId
     */

    @Schema(name = "template_id", example = "d1087efe-41fd-42da-9110-62d35659cf1f", description = "The unique ID of the escape room template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("template_id")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public LevelDTO levelId(String levelId) {
        this.levelId = levelId;
        return this;
    }

    /**
     * The unique ID of the escape room level
     *
     * @return levelId
     */

    @Schema(name = "level_id", example = "d1087efe-41fd-42da-9110-62d35659cf1f", description = "The unique ID of the escape room level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level_id")
    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public LevelDTO levelSequence(Integer levelSequence) {
        this.levelSequence = levelSequence;
        return this;
    }

    /**
     * The sequence number of the level
     *
     * @return levelSequence
     */

    @Schema(name = "level_sequence", example = "1", description = "The sequence number of the level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level_sequence")
    public Integer getLevelSequence() {
        return levelSequence;
    }

    public void setLevelSequence(Integer levelSequence) {
        this.levelSequence = levelSequence;
    }

    public LevelDTO scenes(List<@Valid SceneDTO> scenes) {
        this.scenes = scenes;
        return this;
    }

    public LevelDTO addScenesItem(SceneDTO scenesItem) {
        if (this.scenes == null) {
            this.scenes = new ArrayList<>();
        }
        this.scenes.add(scenesItem);
        return this;
    }

    /**
     * List of scenes in the level
     *
     * @return scenes
     */
    @Valid
    @Schema(name = "scenes", description = "List of scenes in the level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("scenes")
    public List<@Valid SceneDTO> getScenes() {
        return scenes;
    }

    public void setScenes(List<@Valid SceneDTO> scenes) {
        this.scenes = scenes;
    }

    public LevelDTO riddle(RiddleDTO riddle) {
        this.riddle = riddle;
        return this;
    }

    /**
     * Get riddle
     *
     * @return riddle
     */
    @Valid
    @Schema(name = "riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("riddle")
    public RiddleDTO getRiddle() {
        return riddle;
    }

    public void setRiddle(RiddleDTO riddle) {
        this.riddle = riddle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LevelDTO levelDTO = (LevelDTO) o;
        return Objects.equals(this.templateId, levelDTO.templateId) && Objects.equals(this.levelId, levelDTO.levelId)
                && Objects.equals(this.levelSequence, levelDTO.levelSequence)
                && Objects.equals(this.scenes, levelDTO.scenes) && Objects.equals(this.riddle, levelDTO.riddle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, levelId, levelSequence, scenes, riddle);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LevelDTO {\n");
        sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
        sb.append("    levelId: ").append(toIndentedString(levelId)).append("\n");
        sb.append("    levelSequence: ").append(toIndentedString(levelSequence)).append("\n");
        sb.append("    scenes: ").append(toIndentedString(scenes)).append("\n");
        sb.append("    riddle: ").append(toIndentedString(riddle)).append("\n");
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
