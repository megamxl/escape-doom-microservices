package at.escapedoom.player.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * The result of a submitted solution
 */

@Schema(name = "EscapeRoomResult", description = "The result of a submitted solution")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class EscapeRoomResult {

    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        ERROR("ERROR"),

        COMPILED("COMPILED"),

        SUCCESS("SUCCESS"),

        WAITING("WAITING"),

        WON("WON");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String value) {
            for (StatusEnum b : StatusEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private @Nullable StatusEnum status;

    private @Nullable String output;

    public EscapeRoomResult status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     */

    @Schema(name = "status", example = "SUCCESS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("status")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public EscapeRoomResult output(String output) {
        this.output = output;
        return this;
    }

    /**
     * The output of the solution
     *
     * @return output
     */

    @Schema(name = "output", example = "Hello World!", description = "The output of the solution", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("output")
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EscapeRoomResult escapeRoomResult = (EscapeRoomResult) o;
        return Objects.equals(this.status, escapeRoomResult.status)
                && Objects.equals(this.output, escapeRoomResult.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, output);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomResult {\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    output: ").append(toIndentedString(output)).append("\n");
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
