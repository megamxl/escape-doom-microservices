package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
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
 * The escape-room template, base for an EscapeRoom Game
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor

@Schema(name = "EscapeRoomTemplateDTO", description = "The escape-room template, base for an EscapeRoom Game")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class EscapeRoomTemplateDTO {

    private @Nullable String escapeRoomTemplateId;

    private @Nullable String name;

    private @Nullable String description;

    public EscapeRoomTemplateDTO escapeRoomTemplateId(String escapeRoomTemplateId) {
        this.escapeRoomTemplateId = escapeRoomTemplateId;
        return this;
    }

    /**
     * Unique ID for the Template
     *
     * @return escapeRoomTemplateId
     */

    @Schema(name = "escape_room_template_id", example = "b6557071-e7fa-47bc-bdd1-5657ebd325b8", description = "Unique ID for the Template", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_template_id")
    public String getEscapeRoomTemplateId() {
        return escapeRoomTemplateId;
    }

    public void setEscapeRoomTemplateId(String escapeRoomTemplateId) {
        this.escapeRoomTemplateId = escapeRoomTemplateId;
    }

    public EscapeRoomTemplateDTO name(String name) {
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

    public EscapeRoomTemplateDTO description(String description) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EscapeRoomTemplateDTO escapeRoomTemplateDTO = (EscapeRoomTemplateDTO) o;
        return Objects.equals(this.escapeRoomTemplateId, escapeRoomTemplateDTO.escapeRoomTemplateId)
                && Objects.equals(this.name, escapeRoomTemplateDTO.name)
                && Objects.equals(this.description, escapeRoomTemplateDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escapeRoomTemplateId, name, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomTemplateDTO {\n");
        sb.append("    escapeRoomTemplateId: ").append(toIndentedString(escapeRoomTemplateId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
