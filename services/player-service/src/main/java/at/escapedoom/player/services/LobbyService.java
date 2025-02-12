package at.escapedoom.player.services;

import at.escapedoom.player.entity.UserProgress;
import at.escapedoom.player.repository.UserProgressRepository;
import at.escapedoom.player.rest.model.EscapeRoomJoinResponse;
import at.escapedoom.player.rest.model.EscapeRoomState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LobbyService {

    private final UserProgressRepository userProgressRepository;

    public EscapeRoomJoinResponse joinSessionByRoomPin(Long roomPin, String playerName) {

        // TODO Check if the room is in session

        UserProgress andInitializeAUserObject = createAndInitializeAUserObject(roomPin,playerName);

        UserProgress persistedUser = userProgressRepository.save(andInitializeAUserObject);

        log.info("Saved user with Username {} and the identifier {} in room with roomPin {}", persistedUser.getUserName(),  persistedUser.getUserIdentifier(), persistedUser.getRoomPin());

        return buildResponseFromPersistedUser(persistedUser, null);
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
