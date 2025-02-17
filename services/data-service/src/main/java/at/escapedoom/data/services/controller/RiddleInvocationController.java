package at.escapedoom.data.services.controller;

import at.escapedoom.data.rest.api.RiddleApiDelegate;
import at.escapedoom.data.rest.model.CreateRiddleRequest;
import at.escapedoom.data.rest.model.DeleteRiddleRequest;
import at.escapedoom.data.rest.model.Riddle;
import at.escapedoom.data.services.RiddleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RiddleInvocationController implements RiddleApiDelegate {

    private final RiddleService service;

    @Override
    public ResponseEntity<Riddle> createRiddle(CreateRiddleRequest createRiddleRequest) {
        return RiddleApiDelegate.super.createRiddle(createRiddleRequest);
    }

    @Override
    public ResponseEntity<DeleteRiddleRequest> deleteRiddle(String escapeRoomRiddleId) {
        return RiddleApiDelegate.super.deleteRiddle(escapeRoomRiddleId);
    }

    @Override
    public ResponseEntity<List<Riddle>> getAllRiddles() {
        return RiddleApiDelegate.super.getAllRiddles();
    }

    @Override
    public ResponseEntity<Riddle> putRiddle(String escapeRoomRiddleId, CreateRiddleRequest createRiddleRequest) {
        return RiddleApiDelegate.super.putRiddle(escapeRoomRiddleId, createRiddleRequest);
    }
}
