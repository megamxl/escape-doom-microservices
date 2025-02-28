package at.escapedoom.data.delegate;

import at.escapedoom.data.rest.api.LevelApiDelegate;
import at.escapedoom.data.rest.model.DeleteLevelSuccess;
import at.escapedoom.data.rest.model.EscapeRoomLevel;
import at.escapedoom.data.service.LevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LevelApiDelegateImpl implements LevelApiDelegate {

    private final LevelService levelService;

    public LevelApiDelegateImpl(LevelService levelService) {
        this.levelService = levelService;
    }

    @Override
    public ResponseEntity<EscapeRoomLevel> createLevel(EscapeRoomLevel escapeRoomLevel) {
        try {
            EscapeRoomLevel createdLevel = levelService.saveLevel(escapeRoomLevel);
            return ResponseEntity.status(201).body(createdLevel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<DeleteLevelSuccess> deleteLevel(String escapeRoomLevelId) {
        try {
            DeleteLevelSuccess response = levelService.deleteLevel(escapeRoomLevelId);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<EscapeRoomLevel>> getAllLevels() {
        List<EscapeRoomLevel> levels = levelService.getAllLevels();
        return ResponseEntity.ok(levels);
    }

    @Override
    public ResponseEntity<EscapeRoomLevel> getLevel(String escapeRoomLevelId) {
        try {
            EscapeRoomLevel level = levelService.getLevel(escapeRoomLevelId);
            return ResponseEntity.ok(level);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<EscapeRoomLevel> putLevelOfTemplate(String escapeRoomLevelId,
            EscapeRoomLevel escapeRoomLevel) {
        try {
            EscapeRoomLevel updatedLevel = levelService.updateLevel(escapeRoomLevelId, escapeRoomLevel);
            return ResponseEntity.ok(updatedLevel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
