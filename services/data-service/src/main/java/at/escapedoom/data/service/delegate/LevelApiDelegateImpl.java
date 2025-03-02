package at.escapedoom.data.service.delegate;

import at.escapedoom.data.rest.api.LevelApiDelegate;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.EscapeRoomLevelDTO;
import at.escapedoom.data.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LevelApiDelegateImpl implements LevelApiDelegate {

    private final LevelService levelService;

    @Override
    public ResponseEntity<EscapeRoomLevelDTO> createLevel(EscapeRoomLevelDTO request) {
        EscapeRoomLevelDTO createdLevel = levelService.createLevel(request);
        return new ResponseEntity<>(createdLevel, HttpStatus.CREATED);
    }

    /*
     * @Override public ResponseEntity<EscapeRoomLevelDTO> updateLevel(String escapeRoomLevelId, EscapeRoomLevelDTO
     * request) { EscapeRoomLevelDTO updatedLevel = levelService.updateLevel(escapeRoomLevelId, request); return new
     * ResponseEntity<>(updatedLevel, HttpStatus.OK); }
     *
     */

    @Override
    public ResponseEntity<EscapeRoomLevelDTO> getLevel(String escapeRoomLevelId) {
        EscapeRoomLevelDTO level = levelService.getLevel(escapeRoomLevelId);
        return new ResponseEntity<>(level, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EscapeRoomLevelDTO>> getAllLevels() {
        List<EscapeRoomLevelDTO> levels = levelService.getAllLevels();
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeleteLevelSuccessDTO> deleteLevel(String escapeRoomLevelId) {
        DeleteLevelSuccessDTO deleteResult = levelService.deleteLevel(escapeRoomLevelId);
        return new ResponseEntity<>(deleteResult, HttpStatus.OK);
    }
}
