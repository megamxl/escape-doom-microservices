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
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "PositionDTO", description = "The position of a node")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class PositionDTO {

    private @Nullable Double topPercentage;

    private @Nullable Double leftPercentage;

    public PositionDTO topPercentage(Double topPercentage) {
        this.topPercentage = topPercentage;
        return this;
    }

    /**
     * The relative top position of the node
     *
     * @return topPercentage
     */

    @Schema(name = "top_percentage", example = "50.5", description = "The relative top position of the node", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("top_percentage")
    public Double getTopPercentage() {
        return topPercentage;
    }

    public void setTopPercentage(Double topPercentage) {
        this.topPercentage = topPercentage;
    }

    public PositionDTO leftPercentage(Double leftPercentage) {
        this.leftPercentage = leftPercentage;
        return this;
    }

    /**
     * The relative left position of the node
     *
     * @return leftPercentage
     */

    @Schema(name = "left_percentage", example = "22", description = "The relative left position of the node", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("left_percentage")
    public Double getLeftPercentage() {
        return leftPercentage;
    }

    public void setLeftPercentage(Double leftPercentage) {
        this.leftPercentage = leftPercentage;
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
        return Objects.equals(this.topPercentage, positionDTO.topPercentage)
                && Objects.equals(this.leftPercentage, positionDTO.leftPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topPercentage, leftPercentage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PositionDTO {\n");
        sb.append("    topPercentage: ").append(toIndentedString(topPercentage)).append("\n");
        sb.append("    leftPercentage: ").append(toIndentedString(leftPercentage)).append("\n");
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
