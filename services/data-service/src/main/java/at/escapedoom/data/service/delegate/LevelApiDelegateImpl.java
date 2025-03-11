package at.escapedoom.data.service.delegate;

import at.escapedoom.data.rest.api.LevelApiDelegate;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.LevelCreationRequest;
import at.escapedoom.data.rest.model.LevelDTO;
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
    public ResponseEntity<LevelDTO> createLevel(LevelCreationRequest levelCreationRequest) {
        LevelDTO createdLevel = levelService.createLevel(levelCreationRequest);
        return new ResponseEntity<>(createdLevel, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LevelDTO> updateLevel(String levelId, LevelDTO request) {
        LevelDTO updatedLevel = levelService.updateLevel(levelId, request);
        return new ResponseEntity<>(updatedLevel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LevelDTO> getLevel(String levelId) {
        LevelDTO level = levelService.getLevel(levelId);
        return new ResponseEntity<>(level, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LevelDTO>> getAllLevels() {
        List<LevelDTO> levels = levelService.getAllLevels();
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeleteLevelSuccessDTO> deleteLevel(String levelId) {
        DeleteLevelSuccessDTO deleteResult = levelService.deleteLevel(levelId);
        return new ResponseEntity<>(deleteResult, HttpStatus.OK);
    }
}
