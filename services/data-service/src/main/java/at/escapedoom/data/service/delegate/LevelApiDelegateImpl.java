package at.escapedoom.data.service.delegate;

import at.escapedoom.data.rest.api.LevelApiDelegate;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.LevelCreationRequest;
import at.escapedoom.data.rest.model.LevelDTO;
import at.escapedoom.data.rest.model.TemplateCreateRequestDTO;
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
    public ResponseEntity<LevelDTO> createLevel(LevelCreationRequest LevelCreationRequest) {
        LevelDTO createdLevel = levelService.createLevel(LevelCreationRequest);
        return new ResponseEntity<>(createdLevel, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LevelDTO> updateLevel(String LevelId, LevelDTO request) {
        LevelDTO updatedLevel = levelService.updateLevel(LevelId, request);
        return new ResponseEntity<>(updatedLevel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LevelDTO> getLevel(String LevelId) {
        LevelDTO level = levelService.getLevel(LevelId);
        return new ResponseEntity<>(level, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LevelDTO>> getAllLevels() {
        List<LevelDTO> levels = levelService.getAllLevels();
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeleteLevelSuccessDTO> deleteLevel(String LevelId) {
        DeleteLevelSuccessDTO deleteResult = levelService.deleteLevel(LevelId);
        return new ResponseEntity<>(deleteResult, HttpStatus.OK);
    }
}
