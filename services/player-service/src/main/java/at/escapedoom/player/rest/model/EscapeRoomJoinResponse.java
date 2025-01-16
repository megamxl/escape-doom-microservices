package at.escapedoom.player.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.player.rest.model.EscapeRoomState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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

@Schema(name = "EscapeRoomJoinResponse", description = "The escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class EscapeRoomJoinResponse {

  private UUID playerSessionId;

  private EscapeRoomState escapeRoomState;

  private String playerName;

  public EscapeRoomJoinResponse playerSessionId(UUID playerSessionId) {
    this.playerSessionId = playerSessionId;
    return this;
  }

  /**
   * The id of the escape-room session for the player
   * @return playerSessionId
   */
  @Valid 
  @Schema(name = "player_session_id", example = "78787878-f2f4-4c4d-b9c3-e5a7d7f6e8f0", description = "The id of the escape-room session for the player", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("player_session_id")
  public UUID getPlayerSessionId() {
    return playerSessionId;
  }

  public void setPlayerSessionId(UUID playerSessionId) {
    this.playerSessionId = playerSessionId;
  }

  public EscapeRoomJoinResponse escapeRoomState(EscapeRoomState escapeRoomState) {
    this.escapeRoomState = escapeRoomState;
    return this;
  }

  /**
   * Get escapeRoomState
   * @return escapeRoomState
   */
  @Valid 
  @Schema(name = "escape_room_state", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("escape_room_state")
  public EscapeRoomState getEscapeRoomState() {
    return escapeRoomState;
  }

  public void setEscapeRoomState(EscapeRoomState escapeRoomState) {
    this.escapeRoomState = escapeRoomState;
  }

  public EscapeRoomJoinResponse playerName(String playerName) {
    this.playerName = playerName;
    return this;
  }

  /**
   * The name of the player
   * @return playerName
   */
  
  @Schema(name = "player_name", example = "Waschbär", description = "The name of the player", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("player_name")
  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EscapeRoomJoinResponse escapeRoomJoinResponse = (EscapeRoomJoinResponse) o;
    return Objects.equals(this.playerSessionId, escapeRoomJoinResponse.playerSessionId) &&
        Objects.equals(this.escapeRoomState, escapeRoomJoinResponse.escapeRoomState) &&
        Objects.equals(this.playerName, escapeRoomJoinResponse.playerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerSessionId, escapeRoomState, playerName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EscapeRoomJoinResponse {\n");
    sb.append("    playerSessionId: ").append(toIndentedString(playerSessionId)).append("\n");
    sb.append("    escapeRoomState: ").append(toIndentedString(escapeRoomState)).append("\n");
    sb.append("    playerName: ").append(toIndentedString(playerName)).append("\n");
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

