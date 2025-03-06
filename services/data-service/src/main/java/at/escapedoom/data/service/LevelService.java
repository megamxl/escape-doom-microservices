package at.escapedoom.data.service;

import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.LevelCreationRequest;
import at.escapedoom.data.rest.model.LevelDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository repository;
    private final SceneService sceneService;
    private final RiddleService riddleService;

    @Transactional
    public LevelDTO createLevel(LevelCreationRequest creationRequest) {
        assert creationRequest != null;
        assert creationRequest.getTemplateId() != null;

        Level newLevel = Level.builder()
                .levelSequence(creationRequest.getSequence())
                .scenes(Collections.emptyList())
                .templateId(UUID.fromString(creationRequest.getTemplateId())).build();

        newLevel = repository.saveAndFlush(newLevel);

        return getLevelDTO(newLevel);
    }

    private LevelDTO getLevelDTO(Level newLevel) {
        assert newLevel != null;

        return convertToRestModel(newLevel);
    }

    private LevelDTO getLevelDTO(LevelDTO restModel, Level newLevel) {
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
    public LevelDTO updateLevel(String LevelId, LevelDTO restModel) {
        UUID levelUUID = UUID.fromString(LevelId);

        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + LevelId + " not found"));

        level.setLevelSequence(restModel.getLevelSequence());

        level.getScenes().clear();

        return getLevelDTO(restModel, level);
    }

    public LevelDTO getLevel(String LevelId) {
        UUID levelUUID = UUID.fromString(LevelId);
        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + LevelId + " not found"));
        return convertToRestModel(level);
    }

    public List<LevelDTO> getAllLevels() {
        List<Level> levels = repository.findAll();
        return levels.stream().map(this::convertToRestModel).toList();
    }

    @Transactional
    public DeleteLevelSuccessDTO deleteLevel(String LevelId) {
        UUID levelUUID = UUID.fromString(LevelId);
        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + LevelId + " not found"));

        repository.delete(level);
        return new DeleteLevelSuccessDTO().message("Level deleted successfully");
    }

    private LevelDTO convertToRestModel(Level entity) {

        LevelDTO levelDTO = LevelDTO.builder().levelId(entity.getLevelId().toString())
                .levelSequence(entity.getLevelSequence())
                .templateId(entity.getTemplateId().toString())
                .scenes(entity.getScenes().stream().map(sceneService::toSceneDTO).toList()).build();

        if (entity.getRiddle() != null) {
            levelDTO.setRiddle(riddleService.toRestBody(entity.getRiddle()));
        }
        return levelDTO;
    }
}
