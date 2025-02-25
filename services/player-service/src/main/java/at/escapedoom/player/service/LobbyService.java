package at.escapedoom.player.service;

import at.escapedoom.player.utils.MapperFunctions;
import at.escapedoom.spring.redis.data.models.SessionView;
import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.data.redis.PlayerJoinedEvent;
import at.escapedoom.player.rest.model.EscapeRoomJoinResponse;
import at.escapedoom.player.rest.model.EscapeRoomState;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class LobbyService {

    private final UserProgressRepository userProgressRepository;
    private final EscapeRoomSessionRepositoryService escapeRoomSessionRepositoryService;
    private final RedisMessagingService redisMessagingService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EscapeRoomJoinResponse joinSessionByRoomPin(Long roomPin, String playerName) {

        SessionView sessionView = escapeRoomSessionRepositoryService.getSessionInfoByRoomPin(roomPin)
                .orElseThrow(() -> new NoSuchElementException("No Session with this roomPin found"));

        if (!EnumSet.of(at.escapedoom.spring.redis.data.models.EscapeRoomState.OPEN, at.escapedoom.spring.redis.data.models.EscapeRoomState.STARTED).contains(sessionView.getRoomState())) {
            throw new IllegalArgumentException("The roomPin you entered is Not Open or Started");
        }

        UserProgress newUser = createAndInitializeAUserObject(roomPin, playerName);

        UserProgress persistedUser = userProgressRepository.save(newUser);

        log.info("Saved user with Username {} and the identifier {} in room with roomPin {}",
                persistedUser.getUserName(), persistedUser.getUserIdentifier(), persistedUser.getRoomPin());

        try {
            publishJoin(persistedUser);
        } catch (JsonProcessingException e) {
            log.error("Could not publish join event", e);
        }

        return buildResponseFromPersistedUser(persistedUser, MapperFunctions.stateRedisToRedisRest( sessionView.getRoomState()));
    }

    private static EscapeRoomJoinResponse buildResponseFromPersistedUser(UserProgress persistedUser,
            EscapeRoomState escapeRoomState) {
        return EscapeRoomJoinResponse.builder().escapeRoomState(escapeRoomState)
                .playerSessionId(persistedUser.getUserIdentifier()).playerName(persistedUser.getUserName()).build();
    }

    private static UserProgress createAndInitializeAUserObject(Long roomPin, String playerName) {
        return UserProgress.builder().userName(playerName).currentPoints(0L).roomPin(roomPin).currentEscapeRoomLevel(0L)
                .currentPoints(0L).build();
    }

    private void publishJoin(UserProgress userProgress) throws JsonProcessingException {
        redisMessagingService.sendMessage(objectMapper.writeValueAsString(PlayerJoinedEvent.builder()
                .userName(userProgress.getUserName()).userIdentifier(userProgress.getUserIdentifier().toString())
                .roomPin(userProgress.getRoomPin().toString()).build()));
    }

}
