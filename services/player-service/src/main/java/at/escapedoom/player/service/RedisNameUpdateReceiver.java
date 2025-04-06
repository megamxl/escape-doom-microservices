package at.escapedoom.player.service;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.data.redis.PlayerJoinedEvent;
import at.escapedoom.player.ws.PlayerNamesMessage;
import at.escapedoom.player.ws.WSController;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static at.escapedoom.player.utils.MapperFunctions.extractJson;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisNameUpdateReceiver {

    final WSController controller;
    private final UserProgressRepository userProgressRepository;
    private final Gson gson = new Gson();

    public void receiveMessage(String message) {
        String jsonString = extractJson(message);
        log.debug("Received message from Redis: {}", jsonString);
        if (jsonString != null) {
            PlayerJoinedEvent playerJoinedEvent = gson.fromJson(jsonString, PlayerJoinedEvent.class);
            List<UserProgress> userProgressResult = userProgressRepository
                    .getUserNamesByRoomPin(Long.valueOf(playerJoinedEvent.getRoomPin()));
            List<String> playerNames = userProgressResult.stream().map(UserProgress::getUserName).toList();
            PlayerNamesMessage playerNamesMessage = new PlayerNamesMessage(playerNames);

            controller.update(playerNamesMessage, "/topic/player-names/" + playerJoinedEvent.getRoomPin());
        } else {
            log.error("Received message from Redis at Name Update and Json String was null: {}", message);
        }
    }

}
