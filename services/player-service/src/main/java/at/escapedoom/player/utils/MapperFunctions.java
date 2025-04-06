package at.escapedoom.player.utils;

import at.escapedoom.spring.redis.data.models.EscapeRoomState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperFunctions {

    public static EscapeRoomState stateSessionToRedisMapper(
            at.escapedoom.spring.communication.session.model.EscapeRoomState escapeRoomState) {
        return EscapeRoomState.valueOf(escapeRoomState.name());
    }

    public static at.escapedoom.player.rest.model.EscapeRoomState stateRedisToRedisRest(
            EscapeRoomState escapeRoomState) {
        return at.escapedoom.player.rest.model.EscapeRoomState.valueOf(escapeRoomState.name());
    }

    public static String extractJson(String text) {
        Pattern pattern = Pattern.compile("\\{.*\\}");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
