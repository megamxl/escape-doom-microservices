package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.LevelDTO;
import at.escapedoom.data.rest.model.ReadySceneDTO;
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
 * ReadyLevelDTO
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class ReadyLevelDTO {

    private @Nullable LevelDTO level;

    @lombok.Builder.Default
    @Valid
    private List<@Valid ReadySceneDTO> scenes = new ArrayList<>();

    public ReadyLevelDTO level(LevelDTO level) {
        this.level = level;
        return this;
    }

    /**
     * Get level
     *
     * @return level
     */
    @Valid
    @Schema(name = "level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level")
    public LevelDTO getLevel() {
        return level;
    }

    public void setLevel(LevelDTO level) {
        this.level = level;
    }

    public ReadyLevelDTO scenes(List<@Valid ReadySceneDTO> scenes) {
        this.scenes = scenes;
        return this;
    }

    public ReadyLevelDTO addScenesItem(ReadySceneDTO scenesItem) {
        if (this.scenes == null) {
            this.scenes = new ArrayList<>();
        }
        this.scenes.add(scenesItem);
        return this;
    }

    /**
     * Get scenes
     *
     * @return scenes
     */
    @Valid
    @Schema(name = "scenes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("scenes")
    public List<@Valid ReadySceneDTO> getScenes() {
        return scenes;
    }

    public void setScenes(List<@Valid ReadySceneDTO> scenes) {
        this.scenes = scenes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReadyLevelDTO readyLevelDTO = (ReadyLevelDTO) o;
        return Objects.equals(this.level, readyLevelDTO.level) && Objects.equals(this.scenes, readyLevelDTO.scenes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, scenes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReadyLevelDTO {\n");
        sb.append("    level: ").append(toIndentedString(level)).append("\n");
        sb.append("    scenes: ").append(toIndentedString(scenes)).append("\n");
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
