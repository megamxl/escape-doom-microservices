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
 * The position of a node
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "PositionDTO", description = "The position of a node")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class PositionDTO {

    private @Nullable Double top;

    private @Nullable Double left;

    public PositionDTO top(Double top) {
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

    public PositionDTO left(Double left) {
        this.left = left;
        return this;
    }

    /**
     * The relative left position of the node
     *
     * @return left
     */

    @Schema(name = "left", example = "22", description = "The relative left position of the node", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
        PositionDTO positionDTO = (PositionDTO) o;
        return Objects.equals(this.top, positionDTO.top) && Objects.equals(this.left, positionDTO.left);
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, left);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PositionDTO {\n");
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
