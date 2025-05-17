package at.escapedoom.data.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The escape-room template, base for an Game
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "TemplateDTO", description = "The escape-room template, base for an  Game")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class TemplateDTO {

    private @Nullable String templateId;

    private @Nullable String userId;

    private @Nullable String name;

    private @Nullable String description;

    @lombok.Builder.Default
    @Valid
    private List<@Valid LevelDTO> levels = new ArrayList<>();

    public TemplateDTO templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * Unique ID for the Template
     *
     * @return templateId
     */

    @Schema(name = "template_id", example = "b6557071-e7fa-47bc-bdd1-5657ebd325b8", description = "Unique ID for the Template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("template_id")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public TemplateDTO userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Unique ID of the user the escape room belongs to
     *
     * @return userId
     */

    @Schema(name = "user_id", example = "8437ea27-f56f-43d1-a5a1-77e2a2b57e8e", description = "Unique ID of the user the escape room belongs to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TemplateDTO name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Name of the Template
     *
     * @return name
     */

    @Schema(name = "name", example = "SDE24", description = "Name of the Template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TemplateDTO description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Description of the Template
     *
     * @return description
     */

    @Schema(name = "description", example = "Cäsar´s Rätsel", description = "Description of the Template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TemplateDTO levels(List<@Valid LevelDTO> levels) {
        this.levels = levels;
        return this;
    }

    public TemplateDTO addLevelsItem(LevelDTO levelsItem) {
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
        TemplateDTO templateDTO = (TemplateDTO) o;
        return Objects.equals(this.templateId, templateDTO.templateId)
                && Objects.equals(this.userId, templateDTO.userId) && Objects.equals(this.name, templateDTO.name)
                && Objects.equals(this.description, templateDTO.description)
                && Objects.equals(this.levels, templateDTO.levels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, userId, name, description, levels);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemplateDTO {\n");
        sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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
