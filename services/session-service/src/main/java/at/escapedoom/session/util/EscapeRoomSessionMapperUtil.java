package at.escapedoom.session.util;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class EscapeRoomSessionMapperUtil {

    public static EscapeRoomSessionResponse map(EscapeRoomSession escapeRoomSession) {

        return EscapeRoomSessionResponse.builder().state(escapeRoomSession.getState())
                .templateId(escapeRoomSession.getTemplateId()).sessionId(escapeRoomSession.getSessionId())
                .playTime(escapeRoomSession.getPlayTime().intValue()).roomPin(escapeRoomSession.getRoomPin().intValue())
                .createdAt(OffsetDateTime.of(escapeRoomSession.getCreatedAt(), ZoneOffset.UTC))
                .tags(escapeRoomSession.getTags()).build();
    }
}
