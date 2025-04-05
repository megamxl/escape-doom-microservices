package at.escapedoom.player.service;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.data.redis.PlayerJoinedEvent;
import at.escapedoom.player.ws.PlayerNamesMessage;
import at.escapedoom.player.ws.WSController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisReceiver {

    final WSController controller;
    private final UserProgressRepository userProgressRepository;

    public void receiveMessage(String message) {
        String jsonString = extractJson(message);
        log.debug("Received message from Redis: {}", jsonString);
        if (jsonString != null) {
            PlayerJoinedEvent playerJoinedEvent = new Gson().fromJson(jsonString, PlayerJoinedEvent.class);
            List<UserProgress> userProgressResult = userProgressRepository.getUserNamesByRoomPin(Long.valueOf(playerJoinedEvent.getRoomPin()));
            List<String> playerNames = userProgressResult.stream().map(UserProgress::getUserName).toList();
            PlayerNamesMessage playerNamesMessage = new PlayerNamesMessage(playerNames);

            controller.update(playerNamesMessage, "/topic/player-names/" + playerJoinedEvent.getRoomPin());
        }
    }

    private static String extractJson(String text) {
        Pattern pattern = Pattern.compile("\\{.*\\}");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}
