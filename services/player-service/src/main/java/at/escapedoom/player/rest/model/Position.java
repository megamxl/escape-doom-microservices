package at.escapedoom.player.rest.model;

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
 * The position of a node
 */

@Schema(name = "Position", description = "The position of a node")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class Position {

    private @Nullable Double top;

    private @Nullable Double left;

    public Position top(Double top) {
        this.top = top;
        return this;
    }

    /**
     * The relative top position of the node
     *
     * @return top
     */

    @Schema(name = "top", example = "50.5", description = "The relative top position of the node", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("top")
    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }

    public Position left(Double left) {
        this.left = left;
        return this;
    }

    /**
     * The relative left position of the node
     *
     * @return left
     */

    @Schema(name = "left", example = "22.0", description = "The relative left position of the node", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("left")
    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(this.top, position.top) && Objects.equals(this.left, position.left);
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, left);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Position {\n");
        sb.append("    top: ").append(toIndentedString(top)).append("\n");
        sb.append("    left: ").append(toIndentedString(left)).append("\n");
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
