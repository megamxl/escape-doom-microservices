package at.escapedoom.session.util;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.model.SessionResponse;

import java.time.ZoneId;

public class EscapeRoomSessionMapperUtil {

    public static SessionResponse map(EscapeRoomSession escapeRoomSession) {
        return SessionResponse.builder().state(escapeRoomSession.getState())
                .templateId(escapeRoomSession.getTemplateId()).sessionId(escapeRoomSession.getSessionId())
                .playTime(escapeRoomSession.getPlayTime().intValue()).roomPin(escapeRoomSession.getRoomPin().intValue())
                .createdAt(escapeRoomSession.getCreatedAt().atZone(ZoneId.systemDefault()).toOffsetDateTime())
                .startTime(escapeRoomSession.getStartTime() != null
                        ? escapeRoomSession.getStartTime().atZone(ZoneId.systemDefault()).toOffsetDateTime() : null)
                .endTime(escapeRoomSession.getEndTime() != null
                        ? escapeRoomSession.getEndTime().atZone(ZoneId.systemDefault()).toOffsetDateTime() : null)
                .tags(escapeRoomSession.getTags()).build();
    }
}