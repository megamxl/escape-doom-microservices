package at.escapedoom.player.services;

import at.escapedoom.player.domain.SessionView;
import at.escapedoom.player.entity.UserProgress;
import at.escapedoom.player.repository.UserProgressRepository;
import at.escapedoom.player.rest.model.EscapeRoomJoinResponse;
import at.escapedoom.player.rest.model.EscapeRoomState;
import at.escapedoom.player.services.interfaces.EscapeRoomSessionRepositoryService;
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

    public EscapeRoomJoinResponse joinSessionByRoomPin(Long roomPin, String playerName) {

        SessionView sessionView = escapeRoomSessionRepositoryService.getSessionInfoByRoomPin(roomPin).orElseThrow(
                () -> new NoSuchElementException("No Session with this roomPin found"));

        if (!EnumSet.of(EscapeRoomState.OPEN, EscapeRoomState.STARTED).contains(sessionView.getRoomState())) {
            throw new IllegalArgumentException("The roomPin you entered is Not Open or Started");
        }

        UserProgress newUser = createAndInitializeAUserObject(roomPin,playerName);

        UserProgress persistedUser = userProgressRepository.save(newUser);

        log.info("Saved user with Username {} and the identifier {} in room with roomPin {}", persistedUser.getUserName(),  persistedUser.getUserIdentifier(), persistedUser.getRoomPin());

        return buildResponseFromPersistedUser(persistedUser, sessionView.getRoomState());
    }

    private static EscapeRoomJoinResponse buildResponseFromPersistedUser(UserProgress persistedUser, EscapeRoomState escapeRoomState) {
        return EscapeRoomJoinResponse.builder()
                .escapeRoomState(escapeRoomState)
                .playerSessionId(persistedUser.getUserIdentifier())
                .playerName(persistedUser.getUserName())
                .build();
    }

    private static UserProgress createAndInitializeAUserObject( Long roomPin, String playerName) {
        return UserProgress.
                builder().
                userName(playerName)
                .currentPoints(0L)
                .roomPin(roomPin)
                .currentEscapeRoomLevel(0L)
                .currentPoints(0L).
                build();
    }

}
