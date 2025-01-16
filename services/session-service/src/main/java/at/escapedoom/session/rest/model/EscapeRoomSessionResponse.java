package at.escapedoom.session.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.session.rest.model.EscapeRoomState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * The escape-room instance
 */

@Schema(name = "EscapeRoomSessionResponse", description = "The escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class EscapeRoomSessionResponse {

  private EscapeRoomState state;

  private UUID escapeRoomTemplateId;

  private UUID escapeRoomSessionId;

  private Integer playTime = 60;

  private Integer roomPin;

  @Valid
  private List<String> tags = new ArrayList<>();

  public EscapeRoomSessionResponse state(EscapeRoomState state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
   */
  @Valid 
  @Schema(name = "state", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("state")
  public EscapeRoomState getState() {
    return state;
  }

  public void setState(EscapeRoomState state) {
    this.state = state;
  }

  public EscapeRoomSessionResponse escapeRoomTemplateId(UUID escapeRoomTemplateId) {
    this.escapeRoomTemplateId = escapeRoomTemplateId;
    return this;
  }

  /**
   * The id of the escape-room template used
   * @return escapeRoomTemplateId
   */
  @Valid 
  @Schema(name = "escape_room_template_id", example = "c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0", description = "The id of the escape-room template used", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("escape_room_template_id")
  public UUID getEscapeRoomTemplateId() {
    return escapeRoomTemplateId;
  }

  public void setEscapeRoomTemplateId(UUID escapeRoomTemplateId) {
    this.escapeRoomTemplateId = escapeRoomTemplateId;
  }

  public EscapeRoomSessionResponse escapeRoomSessionId(UUID escapeRoomSessionId) {
    this.escapeRoomSessionId = escapeRoomSessionId;
    return this;
  }

  /**
   * The id of the escape-room session
   * @return escapeRoomSessionId
   */
  @Valid 
  @Schema(name = "escape_room_session_id", example = "a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0", description = "The id of the escape-room session", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("escape_room_session_id")
  public UUID getEscapeRoomSessionId() {
    return escapeRoomSessionId;
  }

  public void setEscapeRoomSessionId(UUID escapeRoomSessionId) {
    this.escapeRoomSessionId = escapeRoomSessionId;
  }

  public EscapeRoomSessionResponse playTime(Integer playTime) {
    this.playTime = playTime;
    return this;
  }

  /**
   * The time in minutes the escape-room will be played for
   * minimum: 1
   * maximum: 180
   * @return playTime
   */
  @Min(1) @Max(180) 
  @Schema(name = "play_time", example = "60", description = "The time in minutes the escape-room will be played for", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("play_time")
  public Integer getPlayTime() {
    return playTime;
  }

  public void setPlayTime(Integer playTime) {
    this.playTime = playTime;
  }

  public EscapeRoomSessionResponse roomPin(Integer roomPin) {
    this.roomPin = roomPin;
    return this;
  }

  /**
   * The pin to join the escape-room
   * minimum: 100000
   * maximum: 999999
   * @return roomPin
   */
  @Min(100000) @Max(999999) 
  @Schema(name = "room_pin", example = "420666", description = "The pin to join the escape-room", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("room_pin")
  public Integer getRoomPin() {
    return roomPin;
  }

  public void setRoomPin(Integer roomPin) {
    this.roomPin = roomPin;
  }

  public EscapeRoomSessionResponse tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public EscapeRoomSessionResponse addTagsItem(String tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
   */
  
  @Schema(name = "tags", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tags")
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EscapeRoomSessionResponse escapeRoomSessionResponse = (EscapeRoomSessionResponse) o;
    return Objects.equals(this.state, escapeRoomSessionResponse.state) &&
        Objects.equals(this.escapeRoomTemplateId, escapeRoomSessionResponse.escapeRoomTemplateId) &&
        Objects.equals(this.escapeRoomSessionId, escapeRoomSessionResponse.escapeRoomSessionId) &&
        Objects.equals(this.playTime, escapeRoomSessionResponse.playTime) &&
        Objects.equals(this.roomPin, escapeRoomSessionResponse.roomPin) &&
        Objects.equals(this.tags, escapeRoomSessionResponse.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(state, escapeRoomTemplateId, escapeRoomSessionId, playTime, roomPin, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EscapeRoomSessionResponse {\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    escapeRoomTemplateId: ").append(toIndentedString(escapeRoomTemplateId)).append("\n");
    sb.append("    escapeRoomSessionId: ").append(toIndentedString(escapeRoomSessionId)).append("\n");
    sb.append("    playTime: ").append(toIndentedString(playTime)).append("\n");
    sb.append("    roomPin: ").append(toIndentedString(roomPin)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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

