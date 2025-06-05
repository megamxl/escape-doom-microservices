package at.escapedoom.leaderboard.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.leaderboard.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.leaderboard.rest.model.UserProgress;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * FullExportResponse
 */
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.Setter

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class FullExportResponse {

    private @Nullable EscapeRoomSessionResponse session;

    @lombok.Builder.Default
    @Valid
    private List<@Valid UserProgress> userProgress = new ArrayList<>();

    public FullExportResponse session(EscapeRoomSessionResponse session) {
        this.session = session;
        return this;
    }

    /**
     * Get session
     *
     * @return session
     */
    @Valid
    @Schema(name = "session", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("session")
    public EscapeRoomSessionResponse getSession() {
        return session;
    }

    public void setSession(EscapeRoomSessionResponse session) {
        this.session = session;
    }

    public FullExportResponse userProgress(List<@Valid UserProgress> userProgress) {
        this.userProgress = userProgress;
        return this;
    }

    public FullExportResponse addUserProgressItem(UserProgress userProgressItem) {
        if (this.userProgress == null) {
            this.userProgress = new ArrayList<>();
        }
        this.userProgress.add(userProgressItem);
        return this;
    }

    /**
     * List of user progress for the session.
     *
     * @return userProgress
     */
    @Valid
    @Schema(name = "user_progress", description = "List of user progress for the session.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("user_progress")
    public List<@Valid UserProgress> getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(List<@Valid UserProgress> userProgress) {
        this.userProgress = userProgress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FullExportResponse fullExportResponse = (FullExportResponse) o;
        return Objects.equals(this.session, fullExportResponse.session)
                && Objects.equals(this.userProgress, fullExportResponse.userProgress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, userProgress);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FullExportResponse {\n");
        sb.append("    session: ").append(toIndentedString(session)).append("\n");
        sb.append("    userProgress: ").append(toIndentedString(userProgress)).append("\n");
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
