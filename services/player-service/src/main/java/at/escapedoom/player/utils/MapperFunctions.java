package at.escapedoom.player.utils;

import at.escapedoom.spring.redis.data.models.EscapeRoomState;

public class MapperFunctions {

    public static EscapeRoomState stateSessionToRedisMapper(at.escapedoom.spring.communication.session.model.EscapeRoomState escapeRoomState) {
        return EscapeRoomState.valueOf(escapeRoomState.name());
    }

    public static at.escapedoom.player.rest.model.EscapeRoomState stateRedisToRedisRest(EscapeRoomState escapeRoomState) {
        return at.escapedoom.player.rest.model.EscapeRoomState.valueOf(escapeRoomState.name());
    }
}
