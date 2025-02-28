package at.escapedoom.data.services.controller;

import at.escapedoom.data.rest.api.RiddleApiDelegate;
import at.escapedoom.data.rest.model.Riddle;
import at.escapedoom.data.rest.model.RiddleCreationRequest;
import at.escapedoom.data.rest.model.RiddleDeletionResponse;
import at.escapedoom.data.services.RiddleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RiddleInvocationController implements RiddleApiDelegate {

    private final RiddleService service;

    @Override
    public ResponseEntity<RiddleDeletionResponse> deleteRiddle(String escapeRoomRiddleId) {
        return new ResponseEntity<>(service.deleteRiddle(escapeRoomRiddleId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Riddle>> getAllRiddles() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Riddle> createRiddle(RiddleCreationRequest riddleCreationRequest) {
        return new ResponseEntity<>(service.createRiddle(riddleCreationRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Riddle> putRiddle(String escapeRoomRiddleId, RiddleCreationRequest riddleCreationRequest) {
        return new ResponseEntity<>(service.updateRiddle(riddleCreationRequest, escapeRoomRiddleId), HttpStatus.OK);
    }
}
