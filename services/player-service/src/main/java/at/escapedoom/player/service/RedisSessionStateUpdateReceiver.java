package at.escapedoom.player.service;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.data.redis.PlayerJoinedEvent;
import at.escapedoom.player.ws.PlayerNamesMessage;
import at.escapedoom.player.ws.StateChange;
import at.escapedoom.player.ws.WSController;
import at.escapedoom.spring.redis.data.models.SessionView;
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
public class RedisSessionStateUpdateReceiver {

    final WSController controller;
    private final UserProgressRepository userProgressRepository;
    private final Gson gson = new Gson();

    public void receiveMessage(String message) {
        String jsonString = extractJson(message);

        SessionView sessionView = gson.fromJson(jsonString, SessionView.class);

        log.debug("Received message from Redis: {}", jsonString);

        controller.sendStart(StateChange.builder().state(sessionView.getRoomState()).build(),
                "/topic/player-names/" + sessionView.getRoomPin());
    }

}
