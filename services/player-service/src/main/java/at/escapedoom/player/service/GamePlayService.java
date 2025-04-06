package at.escapedoom.player.service;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import at.escapedoom.player.rest.model.LevelDTO;
import at.escapedoom.player.service.interfaces.CodeCompilerInterface;
import at.escapedoom.player.service.interfaces.EscapeRoomTemplateRepositoryService;
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
    private final CodeCompilerInterface codeCompilerInterface;

    public LevelDTO getCurrentLevelByUserIdentifier(UUID userIdentifier) {

        // check if user with this identifier is registered
        UserProgress user = userProgressRepository.findById(userIdentifier)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with identifier " + userIdentifier));

        LevelDTO level = escapeRoomTemplateRepositoryService.getCompleteTemplateById(user.getTemplateID(),
                (int) (long) user.getCurrentEscapeRoomLevel());
        //
        //
        // TODO get escapeRoom Session via roomPin

        // TODO if playable get the level and return the EscapeRoom Level

        return level;
    }

    public void submitSolutionAttempt(UUID userIdentifier, EscapeRoomSolutionSubmition escapeRoomSolutionSubmition) {
        codeCompilerInterface.queueCodeAttempt(userIdentifier, escapeRoomSolutionSubmition);
    }

}
