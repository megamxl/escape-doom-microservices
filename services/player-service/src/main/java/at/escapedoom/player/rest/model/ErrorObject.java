package at.escapedoom.player.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * An error returned by the API
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "ErrorObject", description = "An error returned by the API")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class ErrorObject {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private @Nullable OffsetDateTime timestamp;

    private @Nullable String path;

    private @Nullable String status;

    private @Nullable String error;

    public ErrorObject timestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * The timestamp of the error
     *
     * @return timestamp
     */
    @Valid
    @Schema(name = "timestamp", example = "2020-01-01T00:00Z", description = "The timestamp of the error", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("timestamp")
    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorObject path(String path) {
        this.path = path;
        return this;
    }

    /**
     * The path of the error
     *
     * @return path
     */

    @Schema(name = "path", example = "/api/v1/escape-room/join", description = "The path of the error", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ErrorObject status(String status) {
        this.status = status;
        return this;
    }

    /**
     * The status of the error
     *
     * @return status
     */

    @Schema(name = "status", example = "404", description = "The status of the error", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorObject error(String error) {
        this.error = error;
        return this;
    }

    /**
     * The error message
     *
     * @return error
     */

    @Schema(name = "error", example = "The error message", description = "The error message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("error")
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorObject errorObject = (ErrorObject) o;
        return Objects.equals(this.timestamp, errorObject.timestamp) && Objects.equals(this.path, errorObject.path)
                && Objects.equals(this.status, errorObject.status) && Objects.equals(this.error, errorObject.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, path, status, error);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorObject {\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    path: ").append(toIndentedString(path)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    error: ").append(toIndentedString(error)).append("\n");
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
