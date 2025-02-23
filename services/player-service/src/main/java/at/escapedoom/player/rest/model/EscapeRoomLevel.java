package at.escapedoom.player.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.player.rest.model.Scene;
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
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "EscapeRoomLevel", description = "The current level of an escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class EscapeRoomLevel {

    @lombok.Builder.Default
    @Valid
    private List<@Valid Scene> scenes = new ArrayList<>();

    private @Nullable String codeSnippet;

    private @Nullable BigDecimal levelSequence;

    public EscapeRoomLevel scenes(List<@Valid Scene> scenes) {
        this.scenes = scenes;
        return this;
    }

    public EscapeRoomLevel addScenesItem(Scene scenesItem) {
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
    public List<@Valid Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<@Valid Scene> scenes) {
        this.scenes = scenes;
    }

    public EscapeRoomLevel codeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
        return this;
    }

    /**
     * The code snippet of the level
     *
     * @return codeSnippet
     */

    @Schema(name = "code_snippet", example = "public static void main(String[] args) { public static string riddle1() { //Your code goes here! } }", description = "The code snippet of the level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("code_snippet")
    public String getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }

    public EscapeRoomLevel levelSequence(BigDecimal levelSequence) {
        this.levelSequence = levelSequence;
        return this;
    }

    /**
     * The sequence number of the level
     *
     * @return levelSequence
     */
    @Valid
    @Schema(name = "level_sequence", example = "1", description = "The sequence number of the level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level_sequence")
    public BigDecimal getLevelSequence() {
        return levelSequence;
    }

    public void setLevelSequence(BigDecimal levelSequence) {
        this.levelSequence = levelSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EscapeRoomLevel escapeRoomLevel = (EscapeRoomLevel) o;
        return Objects.equals(this.scenes, escapeRoomLevel.scenes)
                && Objects.equals(this.codeSnippet, escapeRoomLevel.codeSnippet)
                && Objects.equals(this.levelSequence, escapeRoomLevel.levelSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scenes, codeSnippet, levelSequence);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomLevel {\n");
        sb.append("    scenes: ").append(toIndentedString(scenes)).append("\n");
        sb.append("    codeSnippet: ").append(toIndentedString(codeSnippet)).append("\n");
        sb.append("    levelSequence: ").append(toIndentedString(levelSequence)).append("\n");
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
