package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.data.rest.model.ReadyLevelDTO;
import at.escapedoom.data.rest.model.TemplateDTO;
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
 * A complete and playable escape room
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "ReadyEscapeRoomDTO", description = "A complete and playable escape room")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class ReadyEscapeRoomDTO {

    private @Nullable TemplateDTO template;

    @lombok.Builder.Default
    @Valid
    private List<@Valid ReadyLevelDTO> levels = new ArrayList<>();

    public ReadyEscapeRoomDTO template(TemplateDTO template) {
        this.template = template;
        return this;
    }

    /**
     * Get template
     *
     * @return template
     */
    @Valid
    @Schema(name = "template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("template")
    public TemplateDTO getTemplate() {
        return template;
    }

    public void setTemplate(TemplateDTO template) {
        this.template = template;
    }

    public ReadyEscapeRoomDTO levels(List<@Valid ReadyLevelDTO> levels) {
        this.levels = levels;
        return this;
    }

    public ReadyEscapeRoomDTO addLevelsItem(ReadyLevelDTO levelsItem) {
        if (this.levels == null) {
            this.levels = new ArrayList<>();
        }
        this.levels.add(levelsItem);
        return this;
    }

    /**
     * Get levels
     *
     * @return levels
     */
    @Valid
    @Schema(name = "levels", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("levels")
    public List<@Valid ReadyLevelDTO> getLevels() {
        return levels;
    }

    public void setLevels(List<@Valid ReadyLevelDTO> levels) {
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
        ReadyEscapeRoomDTO readyEscapeRoomDTO = (ReadyEscapeRoomDTO) o;
        return Objects.equals(this.template, readyEscapeRoomDTO.template)
                && Objects.equals(this.levels, readyEscapeRoomDTO.levels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(template, levels);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReadyEscapeRoomDTO {\n");
        sb.append("    template: ").append(toIndentedString(template)).append("\n");
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
