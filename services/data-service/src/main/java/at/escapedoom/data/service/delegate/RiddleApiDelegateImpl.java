package at.escapedoom.data.service.delegate;

import at.escapedoom.data.rest.api.RiddleApiDelegate;
import at.escapedoom.data.rest.model.RiddleCreationRequestDTO;
import at.escapedoom.data.rest.model.RiddleDTO;
import at.escapedoom.data.rest.model.RiddleDeletionResponseDTO;
import at.escapedoom.data.service.RiddleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RiddleApiDelegateImpl implements RiddleApiDelegate {

    private final RiddleService service;

    @Override
    public ResponseEntity<RiddleDeletionResponseDTO> deleteRiddle(String escapeRoomRiddleId) {
        return new ResponseEntity<>(service.deleteRiddle(escapeRoomRiddleId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RiddleDTO>> getAllRiddles() {
        return new ResponseEntity<>(service.getAllRiddles(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RiddleDTO> getRiddleById(String escapeRoomRiddleId) {
        return new ResponseEntity<>(service.getRiddleById(escapeRoomRiddleId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RiddleDTO> createRiddle(RiddleCreationRequestDTO riddleCreationRequest) {
        return new ResponseEntity<>(service.createRiddle(riddleCreationRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RiddleDTO> putRiddle(String escapeRoomRiddleId,
            RiddleCreationRequestDTO riddleCreationRequest) {
        return new ResponseEntity<>(service.updateRiddle(riddleCreationRequest, escapeRoomRiddleId), HttpStatus.OK);
    }
}
