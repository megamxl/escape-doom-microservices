package at.escapedoom.leaderboard.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.leaderboard.rest.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * Represents the progress of user in a session.
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "UserProgress", description = "Represents the progress of user in a session.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class UserProgress {

    private @Nullable UUID escapeRoomSessionId;

    private @Nullable String playerName;

    private @Nullable Integer currentEscapeRoomLevel;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private @Nullable OffsetDateTime lastRiddleSolvedAt;

    private @Nullable Integer score;

    @lombok.Builder.Default
    @Valid
    private List<@Valid Result> results = new ArrayList<>();

    public UserProgress escapeRoomSessionId(UUID escapeRoomSessionId) {
        this.escapeRoomSessionId = escapeRoomSessionId;
        return this;
    }

    /**
     * The session the user participated in.
     *
     * @return escapeRoomSessionId
     */
    @Valid
    @Schema(name = "escape_room_session_id", example = "a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0", description = "The session the user participated in.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_session_id")
    public UUID getEscapeRoomSessionId() {
        return escapeRoomSessionId;
    }

    public void setEscapeRoomSessionId(UUID escapeRoomSessionId) {
        this.escapeRoomSessionId = escapeRoomSessionId;
    }

    public UserProgress playerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    /**
     * The unique ID of the user.
     *
     * @return playerName
     */

    @Schema(name = "player_name", example = "Waschbär", description = "The unique ID of the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("player_name")
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public UserProgress currentEscapeRoomLevel(Integer currentEscapeRoomLevel) {
        this.currentEscapeRoomLevel = currentEscapeRoomLevel;
        return this;
    }

    /**
     * The highest level reached by the user.
     *
     * @return currentEscapeRoomLevel
     */

    @Schema(name = "current_escape_room_level", example = "5", description = "The highest level reached by the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("current_escape_room_level")
    public Integer getCurrentEscapeRoomLevel() {
        return currentEscapeRoomLevel;
    }

    public void setCurrentEscapeRoomLevel(Integer currentEscapeRoomLevel) {
        this.currentEscapeRoomLevel = currentEscapeRoomLevel;
    }

    public UserProgress lastRiddleSolvedAt(OffsetDateTime lastRiddleSolvedAt) {
        this.lastRiddleSolvedAt = lastRiddleSolvedAt;
        return this;
    }

    /**
     * The timestamp of the last riddle solved.
     *
     * @return lastRiddleSolvedAt
     */
    @Valid
    @Schema(name = "last_riddle_solved_at", example = "2025-01-02T15:45Z", description = "The timestamp of the last riddle solved.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("last_riddle_solved_at")
    public OffsetDateTime getLastRiddleSolvedAt() {
        return lastRiddleSolvedAt;
    }

    public void setLastRiddleSolvedAt(OffsetDateTime lastRiddleSolvedAt) {
        this.lastRiddleSolvedAt = lastRiddleSolvedAt;
    }

    public UserProgress score(Integer score) {
        this.score = score;
        return this;
    }

    /**
     * The score of the user. minimum: 0
     *
     * @return score
     */
    @Min(0)
    @Schema(name = "score", example = "30", description = "The score of the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public UserProgress results(List<@Valid Result> results) {
        this.results = results;
        return this;
    }

    public UserProgress addResultsItem(Result resultsItem) {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        this.results.add(resultsItem);
        return this;
    }

    /**
     * List of riddle results for the user.
     *
     * @return results
     */
    @Valid
    @Schema(name = "results", description = "List of riddle results for the user.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("results")
    public List<@Valid Result> getResults() {
        return results;
    }

    public void setResults(List<@Valid Result> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserProgress userProgress = (UserProgress) o;
        return Objects.equals(this.escapeRoomSessionId, userProgress.escapeRoomSessionId)
                && Objects.equals(this.playerName, userProgress.playerName)
                && Objects.equals(this.currentEscapeRoomLevel, userProgress.currentEscapeRoomLevel)
                && Objects.equals(this.lastRiddleSolvedAt, userProgress.lastRiddleSolvedAt)
                && Objects.equals(this.score, userProgress.score) && Objects.equals(this.results, userProgress.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escapeRoomSessionId, playerName, currentEscapeRoomLevel, lastRiddleSolvedAt, score,
                results);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserProgress {\n");
        sb.append("    escapeRoomSessionId: ").append(toIndentedString(escapeRoomSessionId)).append("\n");
        sb.append("    playerName: ").append(toIndentedString(playerName)).append("\n");
        sb.append("    currentEscapeRoomLevel: ").append(toIndentedString(currentEscapeRoomLevel)).append("\n");
        sb.append("    lastRiddleSolvedAt: ").append(toIndentedString(lastRiddleSolvedAt)).append("\n");
        sb.append("    score: ").append(toIndentedString(score)).append("\n");
        sb.append("    results: ").append(toIndentedString(results)).append("\n");
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
