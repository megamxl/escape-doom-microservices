package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.RiddleDTO;
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
 * The current level of an escape-room instance
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "EscapeRoomLevelDTO", description = "The current level of an escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class EscapeRoomLevelDTO {

    private @Nullable String templateId;

    private @Nullable String levelId;

    private @Nullable Integer sequence;

    @lombok.Builder.Default
    @Valid
    private List<@Valid SceneDTO> scenes = new ArrayList<>();

    private @Nullable RiddleDTO riddle;

    public EscapeRoomLevelDTO templateId(String templateId) {
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

    public EscapeRoomLevelDTO levelId(String levelId) {
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

    public EscapeRoomLevelDTO sequence(Integer sequence) {
        this.sequence = sequence;
        return this;
    }

    /**
     * The sequence number of the level
     *
     * @return sequence
     */

    @Schema(name = "sequence", example = "1", description = "The sequence number of the level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("sequence")
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public EscapeRoomLevelDTO scenes(List<@Valid SceneDTO> scenes) {
        this.scenes = scenes;
        return this;
    }

    public EscapeRoomLevelDTO addScenesItem(SceneDTO scenesItem) {
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

    public EscapeRoomLevelDTO riddle(RiddleDTO riddle) {
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
        EscapeRoomLevelDTO escapeRoomLevelDTO = (EscapeRoomLevelDTO) o;
        return Objects.equals(this.templateId, escapeRoomLevelDTO.templateId)
                && Objects.equals(this.levelId, escapeRoomLevelDTO.levelId)
                && Objects.equals(this.sequence, escapeRoomLevelDTO.sequence)
                && Objects.equals(this.scenes, escapeRoomLevelDTO.scenes)
                && Objects.equals(this.riddle, escapeRoomLevelDTO.riddle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, levelId, sequence, scenes, riddle);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomLevelDTO {\n");
        sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
        sb.append("    levelId: ").append(toIndentedString(levelId)).append("\n");
        sb.append("    sequence: ").append(toIndentedString(sequence)).append("\n");
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
