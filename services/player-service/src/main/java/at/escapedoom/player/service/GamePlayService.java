package at.escapedoom.player.service;

import at.escapedoom.player.data.postgres.entity.Result;
import at.escapedoom.player.data.postgres.entity.SolutionAttempt;
import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.ResultRepository;
import at.escapedoom.player.data.postgres.repository.SolutionAttemptRepository;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.rest.model.EscapeRoomResult;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import at.escapedoom.player.rest.model.LevelDTO;
import at.escapedoom.player.service.interfaces.CodeCompilerInterface;
import at.escapedoom.player.service.interfaces.EscapeRoomTemplateRepositoryService;
import at.escapedoom.player.utils.RiddleToFunctionMapper;
import at.escapedoom.spring.redis.data.models.EscapeRoomState;
import at.escapedoom.spring.redis.data.models.SessionView;
import at.escapedoom.spring.redis.data.repositories.SessionViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final ResultRepository resultRepository;

    private final Long POINTS_PER_LVL = 20L;

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

    private at.escapedoom.spring.communication.data.model.LevelDTO getFullLvl(UUID userIdentifier) {
        UserProgress user = userProgressRepository.findById(userIdentifier)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with identifier " + userIdentifier));

        return escapeRoomTemplateRepositoryService.getFullCurrentLevelByUserIdentifier(user.getTemplateID(),
                (int) (long) user.getCurrentEscapeRoomLevel());

    }

    public void submitSolutionAttempt(UUID userIdentifier, EscapeRoomSolutionSubmition escapeRoomSolutionSubmition) {

        at.escapedoom.spring.communication.data.model.LevelDTO template = getFullLvl(userIdentifier);

        String s = RiddleToFunctionMapper.riddleToBackendFunction(escapeRoomSolutionSubmition.getSolution(),
                template.getRiddle().getInput());
        escapeRoomSolutionSubmition.setSolution(s);

        System.out.println(escapeRoomSolutionSubmition.getSolution());

        codeCompilerInterface.queueCodeAttempt(userIdentifier, escapeRoomSolutionSubmition);
    }

    @Transactional
    public EscapeRoomResult getResultsByUserIdentifier(UUID userIdentifier) {

        Optional<SolutionAttempt> byPlayerUUID = solutionAttemptRepository.findByPlayerUUID(userIdentifier);

        if (byPlayerUUID.isEmpty()) {
            throw new NoSuchElementException("Can't find user resubmit Code" + userIdentifier);
        }

        at.escapedoom.spring.communication.data.model.LevelDTO template = getFullLvl(userIdentifier);

        EscapeRoomResult.StatusEnum status;

        if (template.getRiddle().getExpectedOutput().trim().equals(byPlayerUUID.get().getOutput().trim())) {
            int numberOfLvls = escapeRoomTemplateRepositoryService
                    .getNumberOfLevels(UUID.fromString(template.getTemplateId()));

            if (numberOfLvls > byPlayerUUID.get().getCurrentEscapeRoomLevel() + 1) {
                userProgressRepository.updateUserLvl(userIdentifier);
                status = EscapeRoomResult.StatusEnum.SUCCESS;
            } else {
                status = EscapeRoomResult.StatusEnum.WON;
            }
            userProgressRepository.updateUserProgress(userIdentifier, POINTS_PER_LVL);

            Optional<UserProgress> userProgress = userProgressRepository
                    .getUserProgressByUserId(byPlayerUUID.get().getPlayerUUID());

            if (userProgress.isEmpty()) {
                throw new NoSuchElementException(
                        "Can't find user progress with identifier " + byPlayerUUID.get().getPlayerUUID());
            }

            Result result = new Result().builder().awardedPoints(POINTS_PER_LVL.doubleValue())
                    .escapeRoomLevel(byPlayerUUID.get().getCurrentEscapeRoomLevel())
                    .input(byPlayerUUID.get().getCodeSubmition()).solvedLevelAt(LocalDateTime.now())
                    .userProgress(userProgress.get()).build();
            resultRepository.save(result);

        } else {
            status = byPlayerUUID.get().getStatus();
        }

        EscapeRoomResult build = EscapeRoomResult.builder().status(status).output(byPlayerUUID.get().getOutput())
                .build();

        if (byPlayerUUID.get().getStatus() != EscapeRoomResult.StatusEnum.WAITING) {
            solutionAttemptRepository.deleteById(byPlayerUUID.get().getSolutionAttemptId());
        }

        return build;
    }

}
