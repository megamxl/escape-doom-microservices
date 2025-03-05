package at.escapedoom.data.service;

import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.EscapeRoomLevelCreationRequest;
import at.escapedoom.data.rest.model.EscapeRoomLevelDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository repository;
    private final SceneService sceneService;
    private final RiddleService riddleService;

    @Transactional
    public EscapeRoomLevelDTO createLevel(EscapeRoomLevelCreationRequest creationRequest) {
        assert creationRequest != null;
        assert creationRequest.getTemplateId() != null;

        if (creationRequest.getScenes() == null)
            creationRequest.setScenes(Collections.EMPTY_LIST);

        List<Scene> scenes = creationRequest.getScenes().stream().map(sceneService::toDBScene).toList();

        Level newLevel = Level.builder()
                .levelSequence(creationRequest.getSequence())
                .scenes(scenes)
                .templateId(UUID.fromString(creationRequest.getTemplateId()))
                .build();

        newLevel = repository.saveAndFlush(newLevel);

        return getEscapeRoomLevelDTO(newLevel);
    }

    private EscapeRoomLevelDTO getEscapeRoomLevelDTO(Level newLevel) {
        assert newLevel != null;

        return convertToRestModel(newLevel);
    }

    private EscapeRoomLevelDTO getEscapeRoomLevelDTO(EscapeRoomLevelDTO restModel, Level newLevel) {
        if (restModel.getScenes() != null && !restModel.getScenes().isEmpty()) {
            List<Scene> scenes = sceneService.createScenesForLevel(restModel.getScenes(), newLevel);
            newLevel.getScenes().addAll(scenes);
        }

        // TODO: @Mark Change service to handle riddle as One, not list of many
        // if (restModel.getRiddle() != null) {
        // List<Riddle> riddles = riddleService.createRiddlesForLevel(restModel.getRiddle(), newLevel);
        // newLevel.setRiddle(riddle); getRiddle().addAll(riddles);
        // }

        newLevel = repository.saveAndFlush(newLevel);

        return convertToRestModel(newLevel);
    }

    @Transactional
    public EscapeRoomLevelDTO updateLevel(String escapeRoomLevelId, EscapeRoomLevelDTO restModel) {
        UUID levelUUID = UUID.fromString(escapeRoomLevelId);

        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + escapeRoomLevelId + " not found"));

        level.setLevelSequence(restModel.getSequence());

        level.getScenes().clear();

        return getEscapeRoomLevelDTO(restModel, level);
    }

    public EscapeRoomLevelDTO getLevel(String escapeRoomLevelId) {
        UUID levelUUID = UUID.fromString(escapeRoomLevelId);
        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + escapeRoomLevelId + " not found"));
        return convertToRestModel(level);
    }

    public List<EscapeRoomLevelDTO> getAllLevels() {
        List<Level> levels = repository.findAll();
        return levels.stream().map(this::convertToRestModel).toList();
    }

    @Transactional
    public DeleteLevelSuccessDTO deleteLevel(String escapeRoomLevelId) {
        UUID levelUUID = UUID.fromString(escapeRoomLevelId);
        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + escapeRoomLevelId + " not found"));

        repository.delete(level);
        return new DeleteLevelSuccessDTO().message("Level deleted successfully");
    }

    private EscapeRoomLevelDTO convertToRestModel(Level entity) {

        EscapeRoomLevelDTO escapeRoomLevelDTO = EscapeRoomLevelDTO.builder()
                .levelId(entity.getLevelId().toString())
                .sequence(entity.getLevelSequence())
                .scenes(entity.getScenes().stream().map(sceneService::toScene).toList()).build();

        if (entity.getRiddle() != null) {
            escapeRoomLevelDTO.setRiddle(riddleService.toRestBody(entity.getRiddle()));
        }
        return escapeRoomLevelDTO;
    }
}
