package at.escapedoom.player.service;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.rest.model.LevelDTO;
import at.escapedoom.player.service.interfaces.EscapeRoomTemplateRepositoryService;
import at.escapedoom.spring.redis.data.models.EscapeRoomState;
import at.escapedoom.spring.redis.data.models.SessionView;
import at.escapedoom.spring.redis.data.repositories.SessionViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GamePlayService {

    private final UserProgressRepository userProgressRepository;

    private final EscapeRoomTemplateRepositoryService escapeRoomTemplateRepositoryService;

    private final SessionViewRepository sessionViewRepository;

    public LevelDTO getCurrentLevelByUserIdentifier(UUID userIdentifier) {

        // check if user with this identifier is registered
        UserProgress user = userProgressRepository.findById(userIdentifier)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with identifier " + userIdentifier));

        SessionView sessionView = sessionViewRepository.findById(user.getRoomPin()).orElseThrow(
                () -> new NoSuchElementException("Can't find session with identifier " + user.getRoomPin()));

        if (sessionView.getRoomState() != EscapeRoomState.STARTED) {
            throw new IllegalArgumentException(
                    "Can't play the room since the state is set to  " + sessionView.getRoomState());
        }

        return escapeRoomTemplateRepositoryService.getCompleteTemplateById(user.getTemplateID(),
                (int) (long) user.getCurrentEscapeRoomLevel());
    }

}
