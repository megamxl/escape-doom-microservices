package at.escapedoom.leaderboard.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.leaderboard.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.leaderboard.rest.model.UserProgress;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * FullExportRoomPinGet200ResponseInner
 */

@JsonTypeName("_full_export__room_pin__get_200_response_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class FullExportRoomPinGet200ResponseInner {

  private EscapeRoomSessionResponse session;

  @Valid
  private List<@Valid UserProgress> userProgress = new ArrayList<>();

  public FullExportRoomPinGet200ResponseInner session(EscapeRoomSessionResponse session) {
    this.session = session;
    return this;
  }

  /**
   * Get session
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

  public FullExportRoomPinGet200ResponseInner userProgress(List<@Valid UserProgress> userProgress) {
    this.userProgress = userProgress;
    return this;
  }

  public FullExportRoomPinGet200ResponseInner addUserProgressItem(UserProgress userProgressItem) {
    if (this.userProgress == null) {
      this.userProgress = new ArrayList<>();
    }
    this.userProgress.add(userProgressItem);
    return this;
  }

  /**
   * List of user progress for the session.
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
    FullExportRoomPinGet200ResponseInner fullExportRoomPinGet200ResponseInner = (FullExportRoomPinGet200ResponseInner) o;
    return Objects.equals(this.session, fullExportRoomPinGet200ResponseInner.session) &&
        Objects.equals(this.userProgress, fullExportRoomPinGet200ResponseInner.userProgress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(session, userProgress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FullExportRoomPinGet200ResponseInner {\n");
    sb.append("    session: ").append(toIndentedString(session)).append("\n");
    sb.append("    userProgress: ").append(toIndentedString(userProgress)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

