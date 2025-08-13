package at.escapedoom.data.service;

import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.RiddleRepository;
import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.riddle.Riddle;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.mapper.LevelMapper;
import at.escapedoom.data.mapper.SceneMapper;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.LevelCreationRequest;
import at.escapedoom.data.rest.model.LevelDTO;
import at.escapedoom.data.rest.model.SceneDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LevelService {
    // TODO: @Mark Add Logging pls

    private final LevelRepository repository;
    private final SceneService sceneService;
    private final LevelMapper levelMapper;
    private final RiddleRepository riddleRepository;
    private final SceneRepository sceneRepository;
    private final SceneMapper sceneMapper;

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

    @Transactional
    public LevelDTO updateLevel(String levelId, LevelDTO restModel) {
        UUID levelUUID = UUID.fromString(levelId);

        Level level = repository.findById(levelUUID)
                .orElseThrow(() -> new NoSuchElementException("Level with ID " + levelId + " not found"));

        if (restModel.getLevelSequence() != null) {
            level.setLevelSequence(restModel.getLevelSequence());
        }

        if (restModel.getName() != null) {
            level.setName(restModel.getName());
        }

        if (restModel.getScenes() != null) {
            List<Scene> updatedScenes = new ArrayList<>();

            for (SceneDTO sceneDTO : restModel.getScenes()) {
                if (sceneDTO.getSceneId() != null) {
                    sceneService.updateScene(sceneDTO.getSceneId(), sceneMapper.toSceneRequestDTO(sceneDTO));

                    Scene existingScene = sceneRepository.findById(UUID.fromString(sceneDTO.getSceneId()))
                            .orElseThrow(() -> new NoSuchElementException("Scene not found: " + sceneDTO.getSceneId()));
                    updatedScenes.add(existingScene);
                } else {
                    Scene newScene = sceneMapper.toEntity(sceneDTO);
                    newScene.setLevel(level);
                    updatedScenes.add(newScene);
                }
            }

            level.getScenes().clear();
            level.getScenes().addAll(updatedScenes);
        }

        level = repository.saveAndFlush(level);
        return levelMapper.toDTO(level);
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
        return DeleteLevelSuccessDTO.builder().message("Level deleted successfully").build();
    }
}
