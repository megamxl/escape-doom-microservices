package at.escapedoom.data.service;

import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.RiddleRepository;
import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.rest.model.RiddleCreationRequestDTO;
import at.escapedoom.data.rest.model.RiddleDTO;
import at.escapedoom.data.rest.model.RiddleDeletionResponseDTO;
import at.escapedoom.data.utils.LoggerUtils;
import at.escapedoom.data.utils.ReflectionUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static at.escapedoom.data.utils.LoggerUtils.logCreation;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiddleService {

    final RiddleRepository riddleRepository;
    final LevelRepository levelRepository; //FIXME: Use LevelService.update instead when working :)

    public List<RiddleDTO> getAllRiddles() {
        List<Riddle> riddles = riddleRepository.findAll();

        return riddles.stream().map(this::toRestBody).toList();
    }

    public RiddleDTO getRiddleById(@NonNull String uuid) {

        Riddle riddle = riddleRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Riddle with ID: %s not found", uuid)));

        return toRestBody(riddle);
    }

    @Transactional(rollbackFor = Exception.class)
    public RiddleDTO createRiddle(RiddleCreationRequestDTO riddleRequest) {
        assert riddleRequest != null : "Creation request cannot be null";
        assert riddleRequest.getLevelId() != null : "Level id cannot be null";

        levelRepository.findById(UUID.fromString(riddleRequest.getLevelId()))
                .ifPresent(level -> {
                    if (level.getRiddle() != null) {
                        throw new IllegalArgumentException(String.format("Riddle already exists on level %s", level.getLevelId()));
                    }
                });

        Riddle riddle = creationRequestToRiddle(riddleRequest);

        riddleRepository.saveAndFlush(riddle);

        levelRepository.findById(riddle.getLevelId()).ifPresent(level -> {
            level.setRiddle(riddle);
            levelRepository.saveAndFlush(level);
        });

        riddleLog(LoggerUtils.LogType.CREATION, riddle.getRiddleId());
        return toRestBody(riddle);
    }

    @Transactional(rollbackFor = Exception.class)
    public RiddleDTO updateRiddle(RiddleCreationRequestDTO riddleDTO, String uuid) {
        assert riddleDTO != null : "Riddle update request must not be null";
        assert uuid != null : "Riddle UUID must not be null";

        Riddle riddle = riddleRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Riddle with ID: %s not found", uuid)));

        ReflectionUtils.copyNonNullProperties(riddle, riddleDTO);
        riddleRepository.save(riddle);

        riddleLog(LoggerUtils.LogType.UPDATE, riddle.getRiddleId());

        return toRestBody(riddle);
    }

    public RiddleDeletionResponseDTO deleteRiddle(String uuid) throws IllegalArgumentException {
        assert uuid != null : "UUID must not be null";

        UUID riddleId = UUID.fromString(uuid);

        if (riddleRepository.existsById(riddleId)) {
            riddleRepository.deleteById(riddleId);

            riddleLog(LoggerUtils.LogType.DELETION, riddleId);
            return new RiddleDeletionResponseDTO("Riddle deleted successfully");
        }

        throw new NoSuchElementException("There is no such Riddle with ID: " + uuid);
    }

    // region Helper Methods
    public Riddle creationRequestToRiddle(RiddleCreationRequestDTO riddleRequest) {
        assert riddleRequest.getLevelId() != null;

        return Riddle.builder().input(riddleRequest.getInput()).language(riddleRequest.getLanguage())
                .expectedOutput(riddleRequest.getExpectedOutput()).variableName(riddleRequest.getVariableName())
                .levelId(UUID.fromString(riddleRequest.getLevelId()))
                .functionSignature(riddleRequest.getFunctionSignature()).variableName(riddleRequest.getVariableName())
                .build();
    }

    RiddleDTO toRestBody(Riddle riddle) {
        if (riddle == null) {
            return null;
        }
        return RiddleDTO.builder().expectedOutput(riddle.getExpectedOutput()).language(riddle.getLanguage())
                .input(riddle.getInput()).functionSignature(riddle.getFunctionSignature())
                .levelId(riddle.getLevelId().toString())
                .variableName(riddle.getVariableName()).riddleId(riddle.getRiddleId().toString()).build();
    }

    private void riddleLog(LoggerUtils.LogType logType, UUID uuid) {
        logCreation(log, logType, uuid, Riddle.class);
    }
    // endregion
}
