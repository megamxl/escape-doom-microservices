package at.escapedoom.leaderboard.rest.model;

import java.net.URI;
import java.util.Objects;
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
 * Represents metadata for a session in API responses.
 */

@Schema(name = "EscapeRoomSessionResponse", description = "Represents metadata for a session in API responses.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class EscapeRoomSessionResponse {

    /**
     * The state of the session.
     */
    public enum StateEnum {
        OPEN("OPEN"),

        CLOSED("CLOSED"),

        STARTED("STARTED"),

        FINISHED("FINISHED");

        private String value;

        StateEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StateEnum fromValue(String value) {
            for (StateEnum b : StateEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private StateEnum state;

    private Integer playTime = 60;

    private Integer roomPin;

    private UUID escapeRoomTemplateId;

    private UUID escapeRoomSessionId;

    @Valid
    private List<String> tags = new ArrayList<>();

    public EscapeRoomSessionResponse state(StateEnum state) {
        this.state = state;
        return this;
    }

    /**
     * The state of the session.
     *
     * @return state
     */

    @Schema(name = "state", example = "FINISHED", description = "The state of the session.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("state")
    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public EscapeRoomSessionResponse playTime(Integer playTime) {
        this.playTime = playTime;
        return this;
    }

    /**
     * The time in minutes the escape-room will be played for minimum: 1 maximum: 180
     *
     * @return playTime
     */
    @Min(1)
    @Max(180)
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
     * The PIN to join the session.
     *
     * @return roomPin
     */

    @Schema(name = "room_pin", example = "420666", description = "The PIN to join the session.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("room_pin")
    public Integer getRoomPin() {
        return roomPin;
    }

    public void setRoomPin(Integer roomPin) {
        this.roomPin = roomPin;
    }

    public EscapeRoomSessionResponse escapeRoomTemplateId(UUID escapeRoomTemplateId) {
        this.escapeRoomTemplateId = escapeRoomTemplateId;
        return this;
    }

    /**
     * The ID of the escape room template.
     *
     * @return escapeRoomTemplateId
     */
    @Valid
    @Schema(name = "escape_room_template_id", example = "ff95c83b-c8da-4180-8a97-09dc91892a01", description = "The ID of the escape room template.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
     * The unique session ID.
     *
     * @return escapeRoomSessionId
     */
    @Valid
    @Schema(name = "escape_room_session_id", example = "f1d948b9-2928-42f5-a5c8-0af5711514dc", description = "The unique session ID.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("escape_room_session_id")
    public UUID getEscapeRoomSessionId() {
        return escapeRoomSessionId;
    }

    public void setEscapeRoomSessionId(UUID escapeRoomSessionId) {
        this.escapeRoomSessionId = escapeRoomSessionId;
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
     * Tags associated with the session.
     *
     * @return tags
     */

    @Schema(name = "tags", example = "[\"SDE26\",\"CSDC\"]", description = "Tags associated with the session.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
        return Objects.equals(this.state, escapeRoomSessionResponse.state)
                && Objects.equals(this.playTime, escapeRoomSessionResponse.playTime)
                && Objects.equals(this.roomPin, escapeRoomSessionResponse.roomPin)
                && Objects.equals(this.escapeRoomTemplateId, escapeRoomSessionResponse.escapeRoomTemplateId)
                && Objects.equals(this.escapeRoomSessionId, escapeRoomSessionResponse.escapeRoomSessionId)
                && Objects.equals(this.tags, escapeRoomSessionResponse.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, playTime, roomPin, escapeRoomTemplateId, escapeRoomSessionId, tags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomSessionResponse {\n");
        sb.append("    state: ").append(toIndentedString(state)).append("\n");
        sb.append("    playTime: ").append(toIndentedString(playTime)).append("\n");
        sb.append("    roomPin: ").append(toIndentedString(roomPin)).append("\n");
        sb.append("    escapeRoomTemplateId: ").append(toIndentedString(escapeRoomTemplateId)).append("\n");
        sb.append("    escapeRoomSessionId: ").append(toIndentedString(escapeRoomSessionId)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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
