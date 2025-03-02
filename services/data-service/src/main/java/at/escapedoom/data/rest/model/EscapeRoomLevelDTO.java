package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.RiddleDTO;
import at.escapedoom.data.rest.model.SceneDTO;
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

    private @Nullable String escapeRoomTemplateId;

    private @Nullable String escapeRoomLevelId;

    private @Nullable BigDecimal sequence;

    @lombok.Builder.Default
    @Valid
    private List<@Valid SceneDTO> scenes = new ArrayList<>();

    @lombok.Builder.Default
    @Valid
    private List<@Valid RiddleDTO> riddles = new ArrayList<>();

    public EscapeRoomLevelDTO escapeRoomTemplateId(String escapeRoomTemplateId) {
        this.escapeRoomTemplateId = escapeRoomTemplateId;
        return this;
    }

    /**
     * The unique ID of the escape room template
     *
     * @return escapeRoomTemplateId
     */

    @Schema(name = "escape_room_template_id", example = "d1087efe-41fd-42da-9110-62d35659cf1f", description = "The unique ID of the escape room template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_template_id")
    public String getEscapeRoomTemplateId() {
        return escapeRoomTemplateId;
    }

    public void setEscapeRoomTemplateId(String escapeRoomTemplateId) {
        this.escapeRoomTemplateId = escapeRoomTemplateId;
    }

    public EscapeRoomLevelDTO escapeRoomLevelId(String escapeRoomLevelId) {
        this.escapeRoomLevelId = escapeRoomLevelId;
        return this;
    }

    /**
     * The unique ID of the escape room level
     *
     * @return escapeRoomLevelId
     */

    @Schema(name = "escape_room_level_id", example = "d1087efe-41fd-42da-9110-62d35659cf1f", description = "The unique ID of the escape room level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_level_id")
    public String getEscapeRoomLevelId() {
        return escapeRoomLevelId;
    }

    public void setEscapeRoomLevelId(String escapeRoomLevelId) {
        this.escapeRoomLevelId = escapeRoomLevelId;
    }

    public EscapeRoomLevelDTO sequence(BigDecimal sequence) {
        this.sequence = sequence;
        return this;
    }

    /**
     * The sequence number of the level
     *
     * @return sequence
     */
    @Valid
    @Schema(name = "sequence", example = "1", description = "The sequence number of the level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("sequence")
    public BigDecimal getSequence() {
        return sequence;
    }

    public void setSequence(BigDecimal sequence) {
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

    public EscapeRoomLevelDTO riddles(List<@Valid RiddleDTO> riddles) {
        this.riddles = riddles;
        return this;
    }

    public EscapeRoomLevelDTO addRiddlesItem(RiddleDTO riddlesItem) {
        if (this.riddles == null) {
            this.riddles = new ArrayList<>();
        }
        this.riddles.add(riddlesItem);
        return this;
    }

    /**
     * List of riddles in the level
     *
     * @return riddles
     */
    @Valid
    @Schema(name = "riddles", description = "List of riddles in the level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("riddles")
    public List<@Valid RiddleDTO> getRiddles() {
        return riddles;
    }

    public void setRiddles(List<@Valid RiddleDTO> riddles) {
        this.riddles = riddles;
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
        return Objects.equals(this.escapeRoomTemplateId, escapeRoomLevelDTO.escapeRoomTemplateId)
                && Objects.equals(this.escapeRoomLevelId, escapeRoomLevelDTO.escapeRoomLevelId)
                && Objects.equals(this.sequence, escapeRoomLevelDTO.sequence)
                && Objects.equals(this.scenes, escapeRoomLevelDTO.scenes)
                && Objects.equals(this.riddles, escapeRoomLevelDTO.riddles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escapeRoomTemplateId, escapeRoomLevelId, sequence, scenes, riddles);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomLevelDTO {\n");
        sb.append("    escapeRoomTemplateId: ").append(toIndentedString(escapeRoomTemplateId)).append("\n");
        sb.append("    escapeRoomLevelId: ").append(toIndentedString(escapeRoomLevelId)).append("\n");
        sb.append("    sequence: ").append(toIndentedString(sequence)).append("\n");
        sb.append("    scenes: ").append(toIndentedString(scenes)).append("\n");
        sb.append("    riddles: ").append(toIndentedString(riddles)).append("\n");
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
