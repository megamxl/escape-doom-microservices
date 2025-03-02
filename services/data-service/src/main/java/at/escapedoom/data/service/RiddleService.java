package at.escapedoom.data.service;

import at.escapedoom.data.data.RiddleRepository;
import at.escapedoom.data.data.entity.EscapeRoomLevel;
import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.rest.model.RiddleCreationRequestDTO;
import at.escapedoom.data.rest.model.RiddleDTO;
import at.escapedoom.data.rest.model.RiddleDeletionResponseDTO;
import at.escapedoom.data.utils.ReflectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiddleService {

    final RiddleRepository repository;

    public List<RiddleDTO> getAllRiddles() {
        List<Riddle> riddles = repository.findAll();

        return riddles.stream().map(this::toRestBody).toList();
    }

    public Riddle saveRiddle(Riddle riddle) {
        return repository.saveAndFlush(riddle);
    }

    public RiddleDTO getRiddleById(String uuid) {
        assert uuid != null : "Riddle UUID must not be null";

        Riddle riddle = repository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Riddle with ID: %s not found", uuid)));

        return toRestBody(riddle);
    }

    @Transactional
    public RiddleDTO createRiddle(RiddleCreationRequestDTO riddleRequest) {
        assert riddleRequest != null;

        Riddle riddle = creationRequestToRiddle(riddleRequest);
        repository.save(riddle);
        return toRestBody(riddle);
    }


    public RiddleDTO createRiddle(RiddleCreationRequestDTO riddleRequest, String levelId) {
        assert riddleRequest != null && levelId != null;

        Riddle riddle = creationRequestToRiddle(riddleRequest);
        riddle.setEscapeRoomLevel(EscapeRoomLevel.builder().escapeRoomLevelId(UUID.fromString(levelId)).build());
        repository.save(riddle);
        return toRestBody(riddle);
    }

    @Transactional
    public List<Riddle> createRiddlesForLevel(List<RiddleDTO> riddles, EscapeRoomLevel level) {
        List<Riddle> dbRiddles = new ArrayList<>();
        for (RiddleDTO riddle : riddles) {
            RiddleCreationRequestDTO riddleRequest = convertToRiddleCreationRequest(riddle);
            Riddle dbRiddle = creationRequestToRiddle(riddleRequest);
            dbRiddle.setEscapeRoomLevel(level);
            dbRiddle = saveRiddle(dbRiddle);
            dbRiddles.add(dbRiddle);
        }
        return dbRiddles;
    }

    @Transactional
    public RiddleDTO updateRiddle(RiddleCreationRequestDTO riddle, String uuid) {
        assert riddle != null;
        Riddle dbRiddle = repository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Riddle with ID: %s not found", uuid)));

        ReflectionUtils.copyNonNullProperties(dbRiddle, riddle);
        repository.save(dbRiddle);
        return toRestBody(dbRiddle);
    }

    public RiddleDeletionResponseDTO deleteRiddle(String uuid) throws IllegalArgumentException {
        repository.deleteById(UUID.fromString(uuid));
        return new RiddleDeletionResponseDTO("Riddle deleted successfully");
    }

    // region Helper Methods
    private RiddleCreationRequestDTO convertToRiddleCreationRequest(RiddleDTO riddle) {
        return RiddleCreationRequestDTO.builder().input(riddle.getInput()).language(riddle.getLanguage())
                .variableName(riddle.getVariableName()).expectedOutput(riddle.getExpectedOutput())
                .functionSignature(riddle.getFunctionSignature()).build();
    }

    public Riddle creationRequestToRiddle(RiddleCreationRequestDTO riddleRequest) {
        return Riddle.builder()
                .input(riddleRequest.getInput())
                .language(riddleRequest.getLanguage())
                .expectedOutput(riddleRequest.getExpectedOutput())
                .functionSignature(riddleRequest.getFunctionSignature())
                .variableName(riddleRequest.getVariableName())
                .build();
    }


    RiddleDTO toRestBody(Riddle riddle) {
        return RiddleDTO.builder().expectedOutput(riddle.getExpectedOutput()).language(riddle.getLanguage())
                .input(riddle.getInput()).functionSignature(riddle.getFunctionSignature())
                .escapeRoomRiddleId(riddle.getEscapeRoomRiddleId().toString()).build();
    }
    // endregion
}
