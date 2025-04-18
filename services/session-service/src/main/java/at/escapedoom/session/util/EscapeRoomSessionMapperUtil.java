package at.escapedoom.session.util;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.model.SessionResponse;

public class EscapeRoomSessionMapperUtil {

    public static SessionResponse map(EscapeRoomSession escapeRoomSession) {

        return SessionResponse.builder().state(escapeRoomSession.getState())
                .templateId(escapeRoomSession.getEscapeRoomTemplateId())
                .sessionId(escapeRoomSession.getEscapeRoomSessionId())
                .playTime(escapeRoomSession.getPlayTime().intValue()).roomPin(escapeRoomSession.getRoomPin().intValue())
                .tags(escapeRoomSession.getTags()).build();
    }
}
