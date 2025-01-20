package at.escapedoom.session.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * The escape-room template to use
 */

@Schema(name = "EscapeRoomCreation", description = "The escape-room template to use")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class EscapeRoomCreation {

  private UUID escapeRoomTemplateId;

  private Integer playTime = 60;

  public EscapeRoomCreation escapeRoomTemplateId(UUID escapeRoomTemplateId) {
    this.escapeRoomTemplateId = escapeRoomTemplateId;
    return this;
  }

  /**
   * The id of the escape-room template to use
   * @return escapeRoomTemplateId
   */
  @Valid 
  @Schema(name = "escape_room_template_id", example = "c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0", description = "The id of the escape-room template to use", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("escape_room_template_id")
  public UUID getEscapeRoomTemplateId() {
    return escapeRoomTemplateId;
  }

  public void setEscapeRoomTemplateId(UUID escapeRoomTemplateId) {
    this.escapeRoomTemplateId = escapeRoomTemplateId;
  }

  public EscapeRoomCreation playTime(Integer playTime) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EscapeRoomCreation escapeRoomCreation = (EscapeRoomCreation) o;
    return Objects.equals(this.escapeRoomTemplateId, escapeRoomCreation.escapeRoomTemplateId) &&
        Objects.equals(this.playTime, escapeRoomCreation.playTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(escapeRoomTemplateId, playTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EscapeRoomCreation {\n");
    sb.append("    escapeRoomTemplateId: ").append(toIndentedString(escapeRoomTemplateId)).append("\n");
    sb.append("    playTime: ").append(toIndentedString(playTime)).append("\n");
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

