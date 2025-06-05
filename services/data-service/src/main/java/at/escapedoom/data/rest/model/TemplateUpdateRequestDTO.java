package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.LevelDTO;
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
 * The payload for updating an Template
 */
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.Setter

@Schema(name = "TemplateUpdateRequestDTO", description = "The payload for updating an Template")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class TemplateUpdateRequestDTO {

    private @Nullable String name;

    private @Nullable String description;

    @lombok.Builder.Default
    @Valid
    private List<@Valid LevelDTO> levels = new ArrayList<>();

    public TemplateUpdateRequestDTO name(String name) {
        this.name = name;
        return this;
    }

    /**
     * The updated name of the template
     *
     * @return name
     */

    @Schema(name = "name", example = "SDE25", description = "The updated name of the template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TemplateUpdateRequestDTO description(String description) {
        this.description = description;
        return this;
    }

    /**
     * The updated description of the template
     *
     * @return description
     */

    @Schema(name = "description", example = "Updated description for Caesar's Riddle", description = "The updated description of the template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TemplateUpdateRequestDTO levels(List<@Valid LevelDTO> levels) {
        this.levels = levels;
        return this;
    }

    public TemplateUpdateRequestDTO addLevelsItem(LevelDTO levelsItem) {
        if (this.levels == null) {
            this.levels = new ArrayList<>();
        }
        this.levels.add(levelsItem);
        return this;
    }

    /**
     * The updated levels for the escape-room template
     *
     * @return levels
     */
    @Valid
    @Schema(name = "levels", description = "The updated levels for the escape-room template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("levels")
    public List<@Valid LevelDTO> getLevels() {
        return levels;
    }

    public void setLevels(List<@Valid LevelDTO> levels) {
        this.levels = levels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemplateUpdateRequestDTO templateUpdateRequestDTO = (TemplateUpdateRequestDTO) o;
        return Objects.equals(this.name, templateUpdateRequestDTO.name)
                && Objects.equals(this.description, templateUpdateRequestDTO.description)
                && Objects.equals(this.levels, templateUpdateRequestDTO.levels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, levels);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemplateUpdateRequestDTO {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    levels: ").append(toIndentedString(levels)).append("\n");
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
