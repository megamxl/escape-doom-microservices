package at.escapedoom.session.util;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.model.SessionResponse;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class EscapeRoomSessionMapperUtil {

    public static SessionResponse map(EscapeRoomSession escapeRoomSession) {

        return SessionResponse.builder().state(escapeRoomSession.getState())
                .templateId(escapeRoomSession.getTemplateId()).escapeRoomSessionId(escapeRoomSession.getSessionId())
                .playTime(escapeRoomSession.getPlayTime().intValue()).roomPin(escapeRoomSession.getRoomPin().intValue())
                .createdAt(OffsetDateTime.of(escapeRoomSession.getCreatedAt(), ZoneOffset.UTC))
                .tags(escapeRoomSession.getTags()).build();
    }
}
