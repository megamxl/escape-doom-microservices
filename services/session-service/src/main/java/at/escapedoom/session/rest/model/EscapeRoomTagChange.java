package at.escapedoom.session.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * The tag to add or remove
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "EscapeRoomTagChange", description = "The tag to add or remove")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class EscapeRoomTagChange {

    private @Nullable String tagName;

    private @Nullable UUID escapeRoomSessionId;

    public EscapeRoomTagChange tagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    /**
     * The tag to add or remove
     *
     * @return tagName
     */

    @Schema(name = "tag_name", example = "CSDC", description = "The tag to add or remove", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("tag_name")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public EscapeRoomTagChange escapeRoomSessionId(UUID escapeRoomSessionId) {
        this.escapeRoomSessionId = escapeRoomSessionId;
        return this;
    }

    /**
     * The id of the escape-room instance
     *
     * @return escapeRoomSessionId
     */
    @Valid
    @Schema(name = "escape_room_session_id", example = "a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0", description = "The id of the escape-room instance", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_session_id")
    public UUID getEscapeRoomSessionId() {
        return escapeRoomSessionId;
    }

    public void setEscapeRoomSessionId(UUID escapeRoomSessionId) {
        this.escapeRoomSessionId = escapeRoomSessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EscapeRoomTagChange escapeRoomTagChange = (EscapeRoomTagChange) o;
        return Objects.equals(this.tagName, escapeRoomTagChange.tagName)
                && Objects.equals(this.escapeRoomSessionId, escapeRoomTagChange.escapeRoomSessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName, escapeRoomSessionId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomTagChange {\n");
        sb.append("    tagName: ").append(toIndentedString(tagName)).append("\n");
        sb.append("    escapeRoomSessionId: ").append(toIndentedString(escapeRoomSessionId)).append("\n");
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
