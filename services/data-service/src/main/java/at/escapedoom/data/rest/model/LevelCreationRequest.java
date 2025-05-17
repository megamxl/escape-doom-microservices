package at.escapedoom.data.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * The current level of an escape-room instance
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "LevelCreationRequest", description = "The current level of an escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class LevelCreationRequest {

    private @Nullable String name;

    private @Nullable String templateId;

    private @Nullable Integer levelSequence;

    public LevelCreationRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
     * The name of the level
     *
     * @return name
     */

    @Schema(name = "name", example = "Classroom", description = "The name of the level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LevelCreationRequest templateId(String templateId) {
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

    public LevelCreationRequest levelSequence(Integer levelSequence) {
        this.levelSequence = levelSequence;
        return this;
    }

    /**
     * The sequence number of the level
     *
     * @return levelSequence
     */

    @Schema(name = "level_sequence", example = "1", description = "The sequence number of the level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("level_sequence")
    public Integer getLevelSequence() {
        return levelSequence;
    }

    public void setLevelSequence(Integer levelSequence) {
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
        LevelCreationRequest levelCreationRequest = (LevelCreationRequest) o;
        return Objects.equals(this.name, levelCreationRequest.name)
                && Objects.equals(this.templateId, levelCreationRequest.templateId)
                && Objects.equals(this.levelSequence, levelCreationRequest.levelSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, templateId, levelSequence);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LevelCreationRequest {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
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
