package at.escapedoom.session.rest.model;

import java.net.URI;
import java.util.Objects;
import at.escapedoom.session.rest.model.EscapeRoomState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * The escape-room instance
 */
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter

@Schema(name = "EscapeRoomSessionResponse", description = "The escape-room instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public class EscapeRoomSessionResponse {

    private @Nullable EscapeRoomState state;

    private @Nullable UUID templateId;

    private @Nullable UUID sessionId;

    @lombok.Builder.Default
    private Integer playTime = 60;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private @Nullable OffsetDateTime createdAt;

    private @Nullable Integer roomPin;

    @lombok.Builder.Default
    @Valid
    private List<String> tags = new ArrayList<>();

    public EscapeRoomSessionResponse state(EscapeRoomState state) {
        this.state = state;
        return this;
    }

    /**
     * Get state
     *
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

    public EscapeRoomSessionResponse templateId(UUID templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * The id of the escape-room template used
     *
     * @return templateId
     */
    @Valid
    @Schema(name = "template_id", example = "c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0", description = "The id of the escape-room template used", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("template_id")
    public UUID getTemplateId() {
        return templateId;
    }

    public void setTemplateId(UUID templateId) {
        this.templateId = templateId;
    }

    public EscapeRoomSessionResponse sessionId(UUID sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * The id of the escape-room session
     *
     * @return sessionId
     */
    @Valid
    @Schema(name = "session_id", example = "a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0", description = "The id of the escape-room session", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("session_id")
    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
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

    public EscapeRoomSessionResponse createdAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * The timestamp when the escape-room session was created
     *
     * @return createdAt
     */
    @Valid
    @Schema(name = "created_at", example = "2025-04-06T14:30Z", description = "The timestamp when the escape-room session was created", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public EscapeRoomSessionResponse roomPin(Integer roomPin) {
        this.roomPin = roomPin;
        return this;
    }

    /**
     * The pin to join the escape-room minimum: 100000 maximum: 999999
     *
     * @return roomPin
     */
    @Min(100000)
    @Max(999999)
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
     *
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
        return Objects.equals(this.state, escapeRoomSessionResponse.state)
                && Objects.equals(this.templateId, escapeRoomSessionResponse.templateId)
                && Objects.equals(this.sessionId, escapeRoomSessionResponse.sessionId)
                && Objects.equals(this.playTime, escapeRoomSessionResponse.playTime)
                && Objects.equals(this.createdAt, escapeRoomSessionResponse.createdAt)
                && Objects.equals(this.roomPin, escapeRoomSessionResponse.roomPin)
                && Objects.equals(this.tags, escapeRoomSessionResponse.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, templateId, sessionId, playTime, createdAt, roomPin, tags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EscapeRoomSessionResponse {\n");
        sb.append("    state: ").append(toIndentedString(state)).append("\n");
        sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
        sb.append("    sessionId: ").append(toIndentedString(sessionId)).append("\n");
        sb.append("    playTime: ").append(toIndentedString(playTime)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
        sb.append("    roomPin: ").append(toIndentedString(roomPin)).append("\n");
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
