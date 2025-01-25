package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * The result of updating an EscapeRoomTemplate
 */

@Schema(name = "EscapeRoomTemplateUpdateResult", description = "The result of updating an EscapeRoomTemplate")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class EscapeRoomTemplateUpdateResult {

    private @Nullable String message;

    private @Nullable BigDecimal code;

    public EscapeRoomTemplateUpdateResult message(String message) {
        this.message = message;
        return this;
    }

    /**
     * A message indicating the result of the update operation
     *
     * @return message
     */

    @Schema(name = "message", example = "Template updated successfully", description = "A message indicating the result of the update operation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EscapeRoomTemplateUpdateResult code(BigDecimal code) {
        this.code = code;
        return this;
    }

    /**
     * A status code representing the operation result
     *
     * @return code
     */
    @Valid
    @Schema(name = "code", example = "200", description = "A status code representing the operation result", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("code")
    public BigDecimal getCode() {
        return code;
    }

    public void setCode(BigDecimal code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EscapeRoomTemplateUpdateResult escapeRoomTemplateUpdateResult = (EscapeRoomTemplateUpdateResult) o;
        return Objects.equals(this.message, escapeRoomTemplateUpdateResult.message)
                && Objects.equals(this.code, escapeRoomTemplateUpdateResult.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomTemplateUpdateResult {\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
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
