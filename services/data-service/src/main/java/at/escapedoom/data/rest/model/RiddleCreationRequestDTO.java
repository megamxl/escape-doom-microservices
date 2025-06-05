package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.RiddleWrapper;
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
 * Base schema for riddle creation
 */
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.Setter

@Schema(name = "RiddleCreationRequestDTO", description = "Base schema for riddle creation")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class RiddleCreationRequestDTO {

    private @Nullable RiddleWrapper riddle;

    private @Nullable String levelId;

    public RiddleCreationRequestDTO riddle(RiddleWrapper riddle) {
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

    public RiddleCreationRequestDTO levelId(String levelId) {
        this.levelId = levelId;
        return this;
    }

    /**
     * The ID of the Escape Room Level this riddle belongs to
     *
     * @return levelId
     */

    @Schema(name = "level_id", example = "c2d1a3b4-5e6f-47b8-9c9d-0a1b2c3d4e5f", description = "The ID of the Escape Room Level this riddle belongs to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level_id")
    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RiddleCreationRequestDTO riddleCreationRequestDTO = (RiddleCreationRequestDTO) o;
        return Objects.equals(this.riddle, riddleCreationRequestDTO.riddle)
                && Objects.equals(this.levelId, riddleCreationRequestDTO.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riddle, levelId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RiddleCreationRequestDTO {\n");
        sb.append("    riddle: ").append(toIndentedString(riddle)).append("\n");
        sb.append("    levelId: ").append(toIndentedString(levelId)).append("\n");
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
