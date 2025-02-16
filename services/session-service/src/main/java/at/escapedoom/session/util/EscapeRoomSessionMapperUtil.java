package at.escapedoom.session.util;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;

public class EscapeRoomSessionMapperUtil {

    public static EscapeRoomSessionResponse map(EscapeRoomSession escapeRoomSession) {

        return EscapeRoomSessionResponse.builder()
                .state(escapeRoomSession.getState())
                .escapeRoomTemplateId(escapeRoomSession.getEscapeRoomTemplateId())
                .escapeRoomSessionId(escapeRoomSession.getEscapeRoomSessionId())
                .playTime(escapeRoomSession.getPlayTime().intValue())
                .roomPin(escapeRoomSession.getRoomPin().intValue())
                .tags(escapeRoomSession.getTags())
                .build();
    }
}
