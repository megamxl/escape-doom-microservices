package at.escapedoom.leaderboard.rest.model;

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
 * Represents the result of a user&#39;s progress in a session.
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "Result", description = "Represents the result of a user's progress in a session.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class Result {

    private @Nullable Integer currentEscapeRoomLevel;

    private @Nullable String input;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private @Nullable OffsetDateTime solvedDate;

    private @Nullable Integer addedPoints;

    public Result currentEscapeRoomLevel(Integer currentEscapeRoomLevel) {
        this.currentEscapeRoomLevel = currentEscapeRoomLevel;
        return this;
    }

    /**
     * Level of the escape room.
     *
     * @return currentEscapeRoomLevel
     */

    @Schema(name = "current_escape_room_level", example = "3", description = "Level of the escape room.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("current_escape_room_level")
    public Integer getCurrentEscapeRoomLevel() {
        return currentEscapeRoomLevel;
    }

    public void setCurrentEscapeRoomLevel(Integer currentEscapeRoomLevel) {
        this.currentEscapeRoomLevel = currentEscapeRoomLevel;
    }

    public Result input(String input) {
        this.input = input;
        return this;
    }

    /**
     * User's solution attempt.
     *
     * @return input
     */

    @Schema(name = "input", example = "Solution Attempt", description = "User's solution attempt.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("input")
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Result solvedDate(OffsetDateTime solvedDate) {
        this.solvedDate = solvedDate;
        return this;
    }

    /**
     * Date the riddle was solved.
     *
     * @return solvedDate
     */
    @Valid
    @Schema(name = "solved_date", example = "2025-01-02T15:45Z", description = "Date the riddle was solved.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("solved_date")
    public OffsetDateTime getSolvedDate() {
        return solvedDate;
    }

    public void setSolvedDate(OffsetDateTime solvedDate) {
        this.solvedDate = solvedDate;
    }

    public Result addedPoints(Integer addedPoints) {
        this.addedPoints = addedPoints;
        return this;
    }

    /**
     * Points awarded for solving the riddle.
     *
     * @return addedPoints
     */

    @Schema(name = "added_points", example = "100", description = "Points awarded for solving the riddle.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("added_points")
    public Integer getAddedPoints() {
        return addedPoints;
    }

    public void setAddedPoints(Integer addedPoints) {
        this.addedPoints = addedPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result result = (Result) o;
        return Objects.equals(this.currentEscapeRoomLevel, result.currentEscapeRoomLevel)
                && Objects.equals(this.input, result.input) && Objects.equals(this.solvedDate, result.solvedDate)
                && Objects.equals(this.addedPoints, result.addedPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentEscapeRoomLevel, input, solvedDate, addedPoints);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Result {\n");
        sb.append("    currentEscapeRoomLevel: ").append(toIndentedString(currentEscapeRoomLevel)).append("\n");
        sb.append("    input: ").append(toIndentedString(input)).append("\n");
        sb.append("    solvedDate: ").append(toIndentedString(solvedDate)).append("\n");
        sb.append("    addedPoints: ").append(toIndentedString(addedPoints)).append("\n");
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
