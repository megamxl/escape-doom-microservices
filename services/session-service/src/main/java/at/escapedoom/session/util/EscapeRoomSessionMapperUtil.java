package at.escapedoom.session.util;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.model.SessionResponse;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class EscapeRoomSessionMapperUtil {

    public static SessionResponse map(EscapeRoomSession escapeRoomSession) {
        return SessionResponse.builder().state(escapeRoomSession.getState())
                .templateId(escapeRoomSession.getTemplateId()).sessionId(escapeRoomSession.getSessionId())
                .playTime(escapeRoomSession.getPlayTime().intValue()).roomPin(escapeRoomSession.getRoomPin().intValue())
                .createdAt(OffsetDateTime.of(escapeRoomSession.getCreatedAt(), ZoneOffset.UTC))
                .endTime(escapeRoomSession.getEndTime() != null
                        ? escapeRoomSession.getEndTime().atZone(ZoneId.systemDefault()).toOffsetDateTime()
                        : null)
                .tags(escapeRoomSession.getTags()).build();
    }
}