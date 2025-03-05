package at.escapedoom.data.service;

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

    final RiddleRepository repository;

    public List<RiddleDTO> getAllRiddles() {
        List<Riddle> riddles = repository.findAll();

        return riddles.stream().map(this::toRestBody).toList();
    }

    public RiddleDTO getRiddleById(@NonNull String uuid) {

        Riddle riddle = repository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Riddle with ID: %s not found", uuid)));

        return toRestBody(riddle);
    }

    @Transactional(rollbackFor = Exception.class)
    public RiddleDTO createRiddle(RiddleCreationRequestDTO riddleRequest) {
        assert riddleRequest != null : "Creation request cannot be null";

        Riddle riddle = creationRequestToRiddle(riddleRequest);

        repository.save(riddle);

        riddleLog(LoggerUtils.LogType.CREATION, riddle.getEscapeRoomRiddleId());
        return toRestBody(riddle);
    }

    @Transactional(rollbackFor = Exception.class)
    public RiddleDTO updateRiddle(RiddleCreationRequestDTO riddleDTO, String uuid) {
        assert riddleDTO != null : "Riddle update request must not be null";
        assert uuid != null : "Riddle UUID must not be null";

        Riddle riddle = repository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Riddle with ID: %s not found", uuid)));

        ReflectionUtils.copyNonNullProperties(riddle, riddleDTO);
        repository.save(riddle);

        riddleLog(LoggerUtils.LogType.UPDATE, riddle.getEscapeRoomRiddleId());

        return toRestBody(riddle);
    }

    public RiddleDeletionResponseDTO deleteRiddle(String uuid) throws IllegalArgumentException {
        assert uuid != null : "UUID must not be null";

        UUID riddleId = UUID.fromString(uuid);

        if (repository.existsById(riddleId)) {
            repository.deleteById(riddleId);

            riddleLog(LoggerUtils.LogType.DELETION, riddleId);
            return new RiddleDeletionResponseDTO("Riddle deleted successfully");
        }

        throw new NoSuchElementException("There is no such Riddle with ID: " + uuid);
    }

    // region Helper Methods
    private RiddleCreationRequestDTO convertToRiddleCreationRequest(RiddleDTO riddle) {
        return RiddleCreationRequestDTO.builder().input(riddle.getInput()).language(riddle.getLanguage())
                .variableName(riddle.getVariableName()).expectedOutput(riddle.getExpectedOutput())
                .functionSignature(riddle.getFunctionSignature()).build();
    }

    public Riddle creationRequestToRiddle(RiddleCreationRequestDTO riddleRequest) {
        return Riddle.builder().input(riddleRequest.getInput()).language(riddleRequest.getLanguage())
                .expectedOutput(riddleRequest.getExpectedOutput()).variableName(riddleRequest.getVariableName())
                .functionSignature(riddleRequest.getFunctionSignature()).variableName(riddleRequest.getVariableName())
                .build();
    }

    RiddleDTO toRestBody(Riddle riddle) {
        //FIXME: This is trash but it's late and riddle should like ... ask maxl :) <3
        if (riddle == null) { return null; }
        return RiddleDTO.builder().expectedOutput(riddle.getExpectedOutput()).language(riddle.getLanguage())
                .input(riddle.getInput()).functionSignature(riddle.getFunctionSignature())
                .variableName(riddle.getVariableName()).riddleId(riddle.getEscapeRoomRiddleId().toString()).build();
    }

    private void riddleLog(LoggerUtils.LogType logType, UUID uuid) {
        logCreation(log, logType, uuid, Riddle.class);
    }
    // endregion
}
