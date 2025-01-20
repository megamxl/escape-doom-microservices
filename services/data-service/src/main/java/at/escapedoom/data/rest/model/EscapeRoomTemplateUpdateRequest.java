package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.EscapeRoomLevel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * The payload for updating an EscapeRoomTemplate
 */

@Schema(name = "EscapeRoomTemplateUpdateRequest", description = "The payload for updating an EscapeRoomTemplate")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class EscapeRoomTemplateUpdateRequest {

    private String name;

    private String description;

    @Valid
    private List<@Valid EscapeRoomLevel> levels = new ArrayList<>();

    public EscapeRoomTemplateUpdateRequest name(String name) {
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

    public EscapeRoomTemplateUpdateRequest description(String description) {
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

    public EscapeRoomTemplateUpdateRequest levels(List<@Valid EscapeRoomLevel> levels) {
        this.levels = levels;
        return this;
    }

    public EscapeRoomTemplateUpdateRequest addLevelsItem(EscapeRoomLevel levelsItem) {
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
    public List<@Valid EscapeRoomLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<@Valid EscapeRoomLevel> levels) {
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
        EscapeRoomTemplateUpdateRequest escapeRoomTemplateUpdateRequest = (EscapeRoomTemplateUpdateRequest) o;
        return Objects.equals(this.name, escapeRoomTemplateUpdateRequest.name)
                && Objects.equals(this.description, escapeRoomTemplateUpdateRequest.description)
                && Objects.equals(this.levels, escapeRoomTemplateUpdateRequest.levels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, levels);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomTemplateUpdateRequest {\n");
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
