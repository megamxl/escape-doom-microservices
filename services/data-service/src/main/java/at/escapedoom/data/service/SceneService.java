package at.escapedoom.data.service;

import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.entity.*;
import at.escapedoom.data.rest.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SceneService {

    final SceneRepository sceneRepository;

    public List<SceneDTO> getAllScenes() {
        log.info("Getting all scenes");
        return sceneRepository.findAll().stream().map(this::toSceneDTO).toList();
    }

    public SceneDTO getSceneById(String id) {
        assert id != null : "Scene id is null";

        log.info("Getting scene by id: {}", id);

        Optional<Scene> dbScene = sceneRepository.findById(UUID.fromString(id));
        return dbScene.map(this::toSceneDTO)
                .orElseThrow(() -> new IllegalArgumentException("Scene with id " + id + " not found"));
    }

    public SceneDTO createScene(SceneRequestDTO sceneRequest) {
        assert sceneRequest != null : "Scene request is null";

        Scene scene = toDBScene(sceneRequest);

        scene = sceneRepository.saveAndFlush(scene);

        log.info("Created scene with sequence {}", scene.getSceneSequence());
        return toSceneDTO(scene);
    }

    @Transactional
    public List<Scene> createScenesForLevel(List<SceneDTO> scenes, Level level) {
        List<Scene> sceneList = new ArrayList<>();

        log.debug("Creating scenes for level {}", level);

        for (SceneDTO scene : scenes) {
            SceneRequestDTO sceneRequest = toSceneRequestDTO(scene, level);
            Scene dbScene = toDBScene(sceneRequest);
            dbScene.setLevel(level);

            attachNodesToScene(scene, dbScene);

            dbScene = sceneRepository.saveAndFlush(dbScene);
            sceneList.add(dbScene);
        }

        log.info("Created scenes for level {}", level);

        return sceneList;
    }

    private void attachNodesToScene(SceneDTO scene, Scene dbScene) {
        if (scene.getNodes() != null && !scene.getNodes().isEmpty()) {

            List<Node> nodes = new ArrayList<>();

            log.debug("Attaching nodes to scene {}", dbScene);

            for (NodeDTO node : scene.getNodes()) {
                Node dbNode = toDBNode(node);
                dbNode.setScene(dbScene);
                nodes.add(dbNode);
            }

            dbScene.setNodes(nodes);

            log.info("Attached nodes to scene {}", dbScene);
        }
    }

    public SceneDTO updateScene(String escapeRoomSceneId, SceneRequestDTO scene) {
        assert escapeRoomSceneId != null : "Escape room scene id is null";
        assert scene != null : "Scene request is null";

        Scene dbScene = sceneRepository.findById(UUID.fromString(escapeRoomSceneId))
                .orElseThrow(() -> new IllegalArgumentException("Scene with id " + escapeRoomSceneId + " not found"));

        dbScene.setNodes(scene.getNodes().stream().map(this::toDBNode).toList());
        dbScene.setSceneSequence(scene.getSceneSequence());
        dbScene.setName(scene.getName());
        dbScene.setBackgroundImageURI(scene.getBackgroundImageUri());
        if (scene.getLevelId() != null)
            dbScene.setLevelId(UUID.fromString(scene.getLevelId()));

        dbScene = sceneRepository.saveAndFlush(dbScene);
        log.info("Updated scene with sequence {}", dbScene.getSceneSequence());
        return toSceneDTO(dbScene);
    }

    public DeleteLevelResponseDTO deleteScene(String id) {
        assert id != null : "Scene id is null";

        sceneRepository.deleteById(UUID.fromString(id));
        log.info("Deleted scene with id {}", id);
        return new DeleteLevelResponseDTO("Deleted scene successfully");
    }

    // region Helper Methods
    private SceneRequestDTO toSceneRequestDTO(SceneDTO scene, Level level) {
        return SceneRequestDTO.builder().sceneSequence(scene.getSceneSequence()).name(scene.getName())
                .backgroundImageUri(scene.getBackgroundImageUri()).nodes(scene.getNodes())
                .levelId(level.getLevelId().toString()).build();
    }

    SceneDTO toSceneDTO(Scene scene) {
        return SceneDTO.builder().sceneSequence(scene.getSceneSequence()).name(scene.getName())
                .sceneId(scene.getSceneId().toString()).backgroundImageUri(scene.getBackgroundImageURI())
                .levelId(scene.getLevelId().toString()).nodes(scene.getNodes().stream().map(this::toNodeDTO).toList())
                .build();
    }

    Scene toDBScene(SceneDTO sceneRequest) {
        assert sceneRequest.getLevelId() != null;

        return Scene.builder().name(sceneRequest.getName()).sceneSequence(sceneRequest.getSceneSequence())
                .backgroundImageURI(sceneRequest.getBackgroundImageUri())
                .levelId(UUID.fromString(sceneRequest.getLevelId()))
                .nodes(sceneRequest.getNodes().stream().map(this::toDBNode).toList()).build();
    }

    Scene toDBScene(SceneRequestDTO sceneRequest) {
        assert sceneRequest.getLevelId() != null;

        Scene scene = Scene.builder().name(sceneRequest.getName()).sceneSequence(sceneRequest.getSceneSequence())
                .backgroundImageURI(sceneRequest.getBackgroundImageUri())
                .levelId(UUID.fromString(sceneRequest.getLevelId())).nodes(new ArrayList<>()).build();

        if (sceneRequest.getNodes() != null && !sceneRequest.getNodes().isEmpty()) {
            List<Node> nodes = sceneRequest.getNodes().stream().map(this::toDBNode).toList();
            nodes.forEach(node -> node.setScene(scene));
            scene.setNodes(nodes);
        }

        return scene;
    }

    private NodeDTO toNodeDTO(Node node) {
        return NodeDTO.builder().nodeInfo(toNodeInfo(node.getNodeInfo())).nodeType(node.getNodeType())
                .position(toPosition(node.getPosition())).build();
    }

    private NodeInfoDTO toNodeInfo(NodeInfo nodeInfo) {
        return NodeInfoDTO.builder().description(nodeInfo.getDescription()).title(nodeInfo.getTitle())
                .imageURI(nodeInfo.getImageURI()).build();
    }

    private PositionDTO toPosition(Position position) {
        return PositionDTO.builder().left(position.getLeftPercentage()).top(position.getTopPercentage()).build();
    }

    Node toDBNode(NodeDTO node) {
        return Node.builder().nodeType(node.getNodeType()).nodeInfo(toDBNodeInfo(node.getNodeInfo()))
                .position(toDBPosition(node.getPosition())).build();
    }

    private NodeInfo toDBNodeInfo(NodeInfoDTO nodeInfo) {
        return NodeInfo.builder().description(nodeInfo.getDescription()).title(nodeInfo.getTitle())
                .imageURI(nodeInfo.getImageURI()).build();
    }

    private Position toDBPosition(PositionDTO position) {
        return Position.builder().topPercentage(position.getTop()).leftPercentage(position.getLeft()).build();
    }
}
