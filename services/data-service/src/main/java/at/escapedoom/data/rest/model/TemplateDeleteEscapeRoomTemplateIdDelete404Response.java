package at.escapedoom.data.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * TemplateDeleteEscapeRoomTemplateIdDelete404Response
 */

@JsonTypeName("_template_delete__escape_room_template_id__delete_404_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class TemplateDeleteEscapeRoomTemplateIdDelete404Response {

    private String message;

    private Integer code;

    public TemplateDeleteEscapeRoomTemplateIdDelete404Response message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     *
     * @return message
     */

    @Schema(name = "message", example = "The requested resource was not found", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TemplateDeleteEscapeRoomTemplateIdDelete404Response code(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * Get code
     *
     * @return code
     */

    @Schema(name = "code", example = "404", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
        TemplateDeleteEscapeRoomTemplateIdDelete404Response templateDeleteEscapeRoomTemplateIdDelete404Response = (TemplateDeleteEscapeRoomTemplateIdDelete404Response) o;
        return Objects.equals(this.message, templateDeleteEscapeRoomTemplateIdDelete404Response.message)
                && Objects.equals(this.code, templateDeleteEscapeRoomTemplateIdDelete404Response.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemplateDeleteEscapeRoomTemplateIdDelete404Response {\n");
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
