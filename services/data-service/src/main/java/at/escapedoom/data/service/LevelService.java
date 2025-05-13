package at.escapedoom.data.service;

import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.RiddleRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.mapper.LevelMapper;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.LevelCreationRequest;
import at.escapedoom.data.rest.model.LevelDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LevelService {
    // TODO: @Mark Add Logging pls

    private final LevelRepository repository;
    private final SceneService sceneService;
    private final LevelMapper levelMapper;
    private final RiddleRepository riddleRepository;

    @Transactional
    public LevelDTO createLevel(LevelCreationRequest creationRequest) {
        assert creationRequest != null;
        assert creationRequest.getTemplateId() != null;

        Level newLevel = Level.builder().levelSequence(creationRequest.getLevelSequence())
                .name(creationRequest.getName()).scenes(Collections.emptyList())
                .templateId(UUID.fromString(creationRequest.getTemplateId())).build();

        newLevel = repository.saveAndFlush(newLevel);

        return levelMapper.toDTO(newLevel);
    }

    private LevelDTO getLevelDTO(LevelDTO restModel, Level newLevel) {
        if (restModel.getScenes() != null && !restModel.getScenes().isEmpty()) {
            List<Scene> scenes = sceneService.createScenesForLevel(restModel.getScenes(), newLevel);
            newLevel.getScenes().addAll(scenes);
        }

        newLevel = repository.saveAndFlush(newLevel);

        return levelMapper.toDTO(newLevel);
    }

    @Transactional
    public LevelDTO updateLevel(String levelId, LevelDTO restModel) {
        UUID levelUUID = UUID.fromString(levelId);

        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + levelId + " not found"));

        level.setLevelSequence(restModel.getLevelSequence());
        level.setName(restModel.getName());

        level.getScenes().clear();

        return getLevelDTO(restModel, level);
    }

    public LevelDTO getLevelById(String levelId) {
        UUID levelUUID = UUID.fromString(levelId);
        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + levelId + " not found"));

        return levelMapper.toDTO(level);
    }

    public List<LevelDTO> getAllLevels() {
        List<Level> levels = repository.findAll();
        return levels.stream().map(levelMapper::toDTO).toList();
    }

    @Transactional
    public DeleteLevelSuccessDTO deleteLevel(String levelId) {
        UUID levelUUID = UUID.fromString(levelId);
        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + levelId + " not found"));

        Riddle riddle = level.getRiddle();
        if (riddle != null) {
            riddle.setLevel(null);
            riddleRepository.save(riddle);
        }

        Template template = level.getTemplate();
        if (template != null) {
            template.getLevel().remove(level);
        }

        repository.delete(level);
        return new DeleteLevelSuccessDTO().message("Level deleted successfully");
    }

    @Transactional
    public void updateRiddleInLevel(String levelId, Riddle riddle) {
        UUID levelUUID = UUID.fromString(levelId);

        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + levelId + " not found"));

        level.setRiddle(riddle);
        repository.saveAndFlush(level);
    }
}
