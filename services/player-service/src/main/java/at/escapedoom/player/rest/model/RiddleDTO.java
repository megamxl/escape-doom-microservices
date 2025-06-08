package at.escapedoom.player.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.player.rest.model.RiddleWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * Base schema for a riddle
 */
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.Setter

@Schema(name = "RiddleDTO", description = "Base schema for a riddle")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class RiddleDTO {

    private @Nullable String riddleId;

    private @Nullable String levelId;

    private @Nullable RiddleWrapper riddle;

    public RiddleDTO riddleId(String riddleId) {
        this.riddleId = riddleId;
        return this;
    }

    /**
     * The unique ID of the riddle
     *
     * @return riddleId
     */

    @Schema(name = "riddle_id", example = "5830daed-cb7f-47dd-8248-5dee9bf0aa3d", description = "The unique ID of the riddle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("riddle_id")
    public String getRiddleId() {
        return riddleId;
    }

    public void setRiddleId(String riddleId) {
        this.riddleId = riddleId;
    }

    public RiddleDTO levelId(String levelId) {
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

    public RiddleDTO riddle(RiddleWrapper riddle) {
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
    public RiddleWrapper getRiddle() {
        return riddle;
    }

    public void setRiddle(RiddleWrapper riddle) {
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
        RiddleDTO riddleDTO = (RiddleDTO) o;
        return Objects.equals(this.riddleId, riddleDTO.riddleId) && Objects.equals(this.levelId, riddleDTO.levelId)
                && Objects.equals(this.riddle, riddleDTO.riddle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riddleId, levelId, riddle);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RiddleDTO {\n");
        sb.append("    riddleId: ").append(toIndentedString(riddleId)).append("\n");
        sb.append("    levelId: ").append(toIndentedString(levelId)).append("\n");
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
