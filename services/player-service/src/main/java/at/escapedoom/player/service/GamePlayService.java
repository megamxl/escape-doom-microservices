package at.escapedoom.player.service;

import at.escapedoom.player.data.postgres.entity.SolutionAttempt;
import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.SolutionAttemptRepository;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.rest.model.EscapeRoomResult;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import at.escapedoom.player.rest.model.LevelDTO;
import at.escapedoom.player.service.interfaces.CodeCompilerInterface;
import at.escapedoom.player.service.interfaces.EscapeRoomTemplateRepositoryService;
import at.escapedoom.spring.redis.data.models.EscapeRoomState;
import at.escapedoom.spring.redis.data.models.SessionView;
import at.escapedoom.spring.redis.data.repositories.SessionViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GamePlayService {

    private final UserProgressRepository userProgressRepository;
    private final EscapeRoomTemplateRepositoryService escapeRoomTemplateRepositoryService;
    private final CodeCompilerInterface codeCompilerInterface;
    private final SolutionAttemptRepository solutionAttemptRepository;

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

    public void submitSolutionAttempt(UUID userIdentifier, EscapeRoomSolutionSubmition escapeRoomSolutionSubmition) {
        codeCompilerInterface.queueCodeAttempt(userIdentifier, escapeRoomSolutionSubmition);
    }

    public EscapeRoomResult getResultsByUserIdentifier(UUID userIdentifier) {

        Optional<SolutionAttempt> byPlayerUUID = solutionAttemptRepository.findByPlayerUUID(userIdentifier);

        if (byPlayerUUID.isEmpty()) {
            throw new NoSuchElementException("Can't find user resubmit Code" + userIdentifier);
        }

        // TODO check if won

        EscapeRoomResult build = EscapeRoomResult.builder().status(byPlayerUUID.get().getStatus())
                .output(byPlayerUUID.get().getOutput()).build();

        if (byPlayerUUID.get().getStatus() != EscapeRoomResult.StatusEnum.WAITING) {
            solutionAttemptRepository.deleteById(byPlayerUUID.get().getSolutionAttemptId());
        }

        return build;
    }

}
