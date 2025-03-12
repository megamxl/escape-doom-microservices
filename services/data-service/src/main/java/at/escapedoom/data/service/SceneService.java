package at.escapedoom.data.service;

import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.mapper.SceneMapper;
import at.escapedoom.data.rest.model.DeleteLevelResponseDTO;
import at.escapedoom.data.rest.model.SceneDTO;
import at.escapedoom.data.rest.model.SceneRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SceneService {

    private final SceneRepository sceneRepository;
    private final SceneMapper sceneMapper;

    public List<SceneDTO> getAllScenes() {
        log.info("Getting all scenes");
        return sceneMapper.toDTOList(sceneRepository.findAll());
    }

    public SceneDTO getSceneById(String id) {
        assert id != null : "Scene id is null";

        log.info("Getting scene by id: {}", id);

        Optional<Scene> dbScene = sceneRepository.findById(UUID.fromString(id));
        return dbScene.map(sceneMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Scene with id " + id + " not found"));
    }

    public SceneDTO createScene(SceneRequestDTO sceneRequest) {
        assert sceneRequest != null : "Scene request is null";

        Scene scene = sceneMapper.toEntity(sceneRequest);
        scene.setNodes(Collections.EMPTY_LIST);

        scene = sceneRepository.saveAndFlush(scene);

        log.info("Created scene with sequence {}", scene.getSceneSequence());
        return sceneMapper.toDTO(scene);
    }

    @Transactional
    public List<Scene> createScenesForLevel(List<SceneDTO> scenes, Level level) {
        log.debug("Creating scenes for level {}", level);

        List<Scene> sceneList = scenes.stream().map(sceneDTO -> {
            Scene dbScene = sceneMapper.toEntity(sceneDTO);
            dbScene.setLevel(level);
            return sceneRepository.saveAndFlush(dbScene);
        }).toList();

        log.info("Created scenes for level {}", level);

        return sceneList;
    }

    public SceneDTO updateScene(String escapeRoomSceneId, SceneRequestDTO sceneRequest) {
        assert escapeRoomSceneId != null : "Escape room scene id is null";
        assert sceneRequest != null : "Scene request is null";

        Scene dbScene = sceneRepository.findById(UUID.fromString(escapeRoomSceneId))
                .orElseThrow(() -> new IllegalArgumentException("Scene with id " + escapeRoomSceneId + " not found"));

        sceneMapper.toEntity(sceneRequest);
        dbScene.setSceneSequence(sceneRequest.getSceneSequence());
        dbScene.setName(sceneRequest.getName());
        dbScene.setBackgroundImageUri(sceneRequest.getBackgroundImageUri());

        if (sceneRequest.getLevelId() != null) {
            dbScene.setLevelId(UUID.fromString(sceneRequest.getLevelId()));
        }

        dbScene = sceneRepository.saveAndFlush(dbScene);
        log.info("Updated scene with sequence {}", dbScene.getSceneSequence());
        return sceneMapper.toDTO(dbScene);
    }

    public DeleteLevelResponseDTO deleteScene(String id) {
        assert id != null : "Scene id is null";

        sceneRepository.deleteById(UUID.fromString(id));
        log.info("Deleted scene with id {}", id);
        return new DeleteLevelResponseDTO("Deleted scene successfully");
    }
}
