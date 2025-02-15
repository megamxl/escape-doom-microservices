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
 * The escape-room instance to join
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "EscapeRoomJoin", description = "The escape-room instance to join")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class EscapeRoomJoin {

    private @Nullable Integer roomPin;

    private @Nullable String playerName;

    public EscapeRoomJoin roomPin(Integer roomPin) {
        this.roomPin = roomPin;
        return this;
    }

    /**
     * The room-pin to join the escape-room minimum: 100000 maximum: 999999
     *
     * @return roomPin
     */
    @Min(100000)
    @Max(999999)
    @Schema(name = "room_pin", example = "420666", description = "The room-pin to join the escape-room", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("room_pin")
    public Integer getRoomPin() {
        return roomPin;
    }

    public void setRoomPin(Integer roomPin) {
        this.roomPin = roomPin;
    }

    public EscapeRoomJoin playerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    /**
     * The name of the player
     *
     * @return playerName
     */
    @Size(min = 1, max = 128)
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
        EscapeRoomJoin escapeRoomJoin = (EscapeRoomJoin) o;
        return Objects.equals(this.roomPin, escapeRoomJoin.roomPin)
                && Objects.equals(this.playerName, escapeRoomJoin.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomPin, playerName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomJoin {\n");
        sb.append("    roomPin: ").append(toIndentedString(roomPin)).append("\n");
        sb.append("    playerName: ").append(toIndentedString(playerName)).append("\n");
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
