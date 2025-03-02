package at.escapedoom.data.service;

import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.entity.EscapeRoomLevel;
import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.EscapeRoomLevelDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@PreAuthorize("hasRole('LECTOR')")
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository repository;
    private final SceneService sceneService;
    private final RiddleService riddleService;

    @Transactional
    public EscapeRoomLevelDTO createLevel(EscapeRoomLevelDTO restModel) {

        EscapeRoomLevel newLevel = EscapeRoomLevel.builder().levelSequence(restModel.getSequence().intValue())
                .scenes(new ArrayList<>()).riddles(new ArrayList<>()).build();
        newLevel = repository.saveAndFlush(newLevel);

        if (restModel.getScenes() != null && !restModel.getScenes().isEmpty()) {
            List<Scene> scenes = sceneService.createScenesForLevel(restModel.getScenes(), newLevel);
            newLevel.getScenes().addAll(scenes);
        }

        if (restModel.getRiddles() != null && !restModel.getRiddles().isEmpty()) {
            List<Riddle> riddles = riddleService.createRiddlesForLevel(restModel.getRiddles(), newLevel);
            newLevel.getRiddles().addAll(riddles);
        }

        newLevel = repository.saveAndFlush(newLevel);

        return convertToRestModel(newLevel);
    }
    /*
     * @Transactional public EscapeRoomLevelDTO updateLevel(String escapeRoomLevelId, EscapeRoomLevelDTO restModel) {
     * UUID levelUUID = UUID.fromString(escapeRoomLevelId);
     *
     * EscapeRoomLevel level = repository.findById(levelUUID) .orElseThrow(() -> new
     * NoSuchElementException("Level with ID " + escapeRoomLevelId + " not found"));
     *
     * level.setLevelSequence(restModel.getSequence().intValue());
     *
     * level.getScenes().clear(); level.getRiddles().clear();
     *
     * if (restModel.getScenes() != null && !restModel.getScenes().isEmpty()) { List<Scene> scenes =
     * sceneService.createScenesForLevel(restModel.getScenes(), level); level.getScenes().addAll(scenes); }
     *
     * if (restModel.getRiddles() != null && !restModel.getRiddles().isEmpty()) { List<Riddle> riddles =
     * riddleService.createRiddlesForLevel(restModel.getRiddles(), level); level.getRiddles().addAll(riddles); }
     *
     * level = repository.saveAndFlush(level);
     *
     * return convertToRestModel(level); }
     *
     */

    public EscapeRoomLevelDTO getLevel(String escapeRoomLevelId) {
        UUID levelUUID = UUID.fromString(escapeRoomLevelId);
        EscapeRoomLevel level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + escapeRoomLevelId + " not found"));
        return convertToRestModel(level);
    }

    public List<EscapeRoomLevelDTO> getAllLevels() {
        List<EscapeRoomLevel> levels = repository.findAll();
        return levels.stream().map(this::convertToRestModel).toList();
    }

    @Transactional
    public DeleteLevelSuccessDTO deleteLevel(String escapeRoomLevelId) {
        UUID levelUUID = UUID.fromString(escapeRoomLevelId);
        EscapeRoomLevel level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + escapeRoomLevelId + " not found"));

        repository.delete(level);
        return new DeleteLevelSuccessDTO().message("Level deleted successfully");
    }

    private EscapeRoomLevelDTO convertToRestModel(EscapeRoomLevel entity) {
        return EscapeRoomLevelDTO.builder().escapeRoomLevelId(entity.getEscapeRoomLevelId().toString())
                .sequence(BigDecimal.valueOf(entity.getLevelSequence()))
                .scenes(entity.getScenes().stream().map(sceneService::toScene).toList())
                .riddles(entity.getRiddles().stream().map(riddleService::toRestBody).toList()).build();
    }
}
