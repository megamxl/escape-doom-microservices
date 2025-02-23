package at.escapedoom.data.services;

import at.escapedoom.data.data.entity.CodingRiddle;
import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.data.repository.RiddleRepository;
import at.escapedoom.data.rest.model.CreateRiddleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('LECTOR')")
@Slf4j
public class RiddleService {

    private final RiddleRepository repository;

    public at.escapedoom.data.rest.model.Riddle toRestBody(Riddle riddle) {

        switch (riddle) {
        case CodingRiddle codingRiddle -> {
            return at.escapedoom.data.rest.model.CodingRiddle.builder()
                    .escapeRoomRiddleId(codingRiddle.getEscapeRoomRiddleId().toString())
                    .type(at.escapedoom.data.rest.model.Riddle.TypeEnum.CODING_RIDDLE).expectedOutput(codingRiddle.getExpectedOutput()).build();
        }
        default -> throw new IllegalStateException("Unexpected value: " + riddle);
        }
    }

    public List<at.escapedoom.data.rest.model.Riddle> getAll() {
        List<Riddle> riddles = repository.findAll();

        return riddles.stream().map(this::toRestBody).toList();
    }

    public at.escapedoom.data.rest.model.Riddle createRiddle(CreateRiddleRequest riddle) {

        switch (riddle) {
        case CodingRiddle creationRequest -> {
            CodingRiddle codingRiddle = creationRequestToCodingRiddle(creationRequest);
            repository.save(codingRiddle);
            return toRestBody(codingRiddle);
        }
        default -> throw new IllegalArgumentException("Riddle type not supported: " + riddle);
        }
    }

    public at.escapedoom.data.rest.model.Riddle updateRiddle(at.escapedoom.data.rest.model.Riddle riddle) {
        baseRiddleAsserts(riddle);

        Riddle r = repository.findById(UUID.fromString(riddle.getEscapeRoomRiddleId())).get();

        switch (r) {
        case CodingRiddle codingRiddle -> {
            log.info("Updating coding riddle");
        }
        default -> throw new IllegalStateException("Unexpected value: " + riddle);
        }
        repository.save(r);
        return riddle;
    }

    public void deleteRiddle(at.escapedoom.data.rest.model.Riddle riddle) {
        baseRiddleAsserts(riddle);

        repository.deleteById(UUID.fromString(riddle.getEscapeRoomRiddleId()));
    }

    private void baseRiddleAsserts(at.escapedoom.data.rest.model.Riddle riddle) {
        assert riddle != null : "Riddle is null";
        assert riddle.getEscapeRoomRiddleId() != null : "Escape Room Riddle Id is null";
    }

    private CodingRiddle creationRequestToCodingRiddle(CodingRiddle creationRequest) {
        assert creationRequest.getEscapeRoomRiddleId() != null : "Escape Room Id is null";

        new CodingRiddle();
        return CodingRiddle.builder().escapeRoomRiddleId(creationRequest.getEscapeRoomRiddleId())
                .input(creationRequest.getInput()).language(creationRequest.getLanguage())
                .variableName(creationRequest.getVariableName()).expectedOutput(creationRequest.getExpectedOutput())
                .functionSignature(creationRequest.getFunctionSignature()).build();
    }
}