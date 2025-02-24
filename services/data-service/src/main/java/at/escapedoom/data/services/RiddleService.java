package at.escapedoom.data.services;

import at.escapedoom.data.data.entity.DBRiddle;
import at.escapedoom.data.data.repository.RiddleRepository;
import at.escapedoom.data.rest.model.Riddle;
import at.escapedoom.data.rest.model.RiddleCreationRequest;
import at.escapedoom.data.rest.model.RiddleDeletionResponse;
import at.escapedoom.data.utils.ReflectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('LECTOR')")
@Slf4j
public class RiddleService {

    private final RiddleRepository repository;

    public List<Riddle> getAll() {
        List<DBRiddle> riddles = repository.findAll();

        return riddles.stream().map(this::toRestBody).toList();
    }

    public Riddle createRiddle(RiddleCreationRequest riddle) {
        assert riddle != null;

        DBRiddle dbRiddle = creationRequestToRiddle(riddle);
        repository.save(dbRiddle);
        return toRestBody(dbRiddle);
    }

    @Transactional
    public Riddle updateRiddle(RiddleCreationRequest riddle, String uuid) {
        assert riddle != null;

        DBRiddle dbRiddle = repository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Riddle with ID: %s not found", uuid)));

        ReflectionUtils.copyNonNullProperties(dbRiddle, riddle);
        repository.save(dbRiddle);
        return toRestBody(dbRiddle);
    }

    public RiddleDeletionResponse deleteRiddle(String uuid) {
        repository.deleteById(UUID.fromString(uuid));
        return new RiddleDeletionResponse("Riddle deleted successfully");
    }

    // region Utils functions
    private DBRiddle creationRequestToRiddle(RiddleCreationRequest creationRequest) {
        new DBRiddle();
        return DBRiddle.builder().input(creationRequest.getInput()).language(creationRequest.getLanguage())
                .variableName(creationRequest.getVariableName()).expectedOutput(creationRequest.getExpectedOutput())
                .functionSignature(creationRequest.getFunctionSignature()).build();
    }

    private Riddle toRestBody(DBRiddle riddle) throws IllegalArgumentException {
        return Riddle.builder().expectedOutput(riddle.getExpectedOutput()).language(riddle.getLanguage())
                .input(riddle.getInput()).functionSignature(riddle.getFunctionSignature())
                .escapeRoomRiddleId(riddle.getEscapeRoomRiddleId().toString()).build();
    }
    // endregion
}
