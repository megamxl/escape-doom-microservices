package at.escapedoom.player.ws;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.spring.security.KeycloakUserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class WSEventListener {

    final WSController controller;
    private final UserProgressRepository userProgressRepository;

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        log.debug("WS Received a session connected event" + event.toString());
    }

    @EventListener
    private void handleSessionSubscribed(SessionSubscribeEvent event) {
        log.debug("WS Received a session sub event" + event.toString());
        String topic = (String) event.getMessage().getHeaders().get("simpDestination");
        String roomPin = topic.substring("/topic/player-names/".length());
        List<UserProgress> userProgressResult = userProgressRepository.getUserNamesByRoomPin(Long.valueOf(roomPin));

        List<String> playerNames = userProgressResult.stream().map(UserProgress::getUserName).toList();

        PlayerNamesMessage message = new PlayerNamesMessage(playerNames);

        this.controller.update(message, topic);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        log.debug("WS Received a session disconnect event" + event.toString());
    }
}
