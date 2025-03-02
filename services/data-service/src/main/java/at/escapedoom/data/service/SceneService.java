package at.escapedoom.data.service;

import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.entity.*;
import at.escapedoom.data.rest.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static at.escapedoom.data.utils.ReflectionUtils.copyNonNullProperties;

@Slf4j
@Service
@PreAuthorize("hasRole('LECTOR')")
@RequiredArgsConstructor
public class SceneService {

    final SceneRepository sceneRepository;

    public List<SceneDTO> getAllScenes() {
        log.info("Getting all scenes");
        return sceneRepository.findAll().stream().map(this::toScene).toList();
    }

    public SceneDTO getScene(String id) {
        Optional<Scene> dbScene = sceneRepository.findById(UUID.fromString(id));
        return dbScene.map(this::toScene)
                .orElseThrow(() -> new IllegalArgumentException("Scene with id " + id + " not found"));
    }

    public SceneDTO createScene(SceneRequestDTO sceneRequest) {
        Scene scene = toDBScene(sceneRequest);
        scene = sceneRepository.saveAndFlush(scene);
        log.info("Created scene with sequence {}", scene.getSceneSequence());
        return toScene(scene);
    }

    public SceneDTO createScene(SceneRequestDTO sceneRequest, String levelId) {
        Scene scene = toDBScene(sceneRequest);
        scene.setEscapeRoomLevel(EscapeRoomLevel.builder().escapeRoomLevelId(UUID.fromString(levelId)).build()); // Only
                                                                                                                 // setting
                                                                                                                 // ID
                                                                                                                 // reference
        scene = sceneRepository.saveAndFlush(scene);
        log.info("Created scene with sequence {} for level {}", scene.getSceneSequence(), levelId);
        return toScene(scene);
    }

    @Transactional
    public List<Scene> createScenesForLevel(List<SceneDTO> scenes, EscapeRoomLevel level) {
        List<Scene> dbScenes = new ArrayList<>();

        for (SceneDTO scene : scenes) {
            SceneRequestDTO sceneRequest = convertToSceneRequest(scene, level);
            Scene dbScene = toDBScene(sceneRequest);
            dbScene.setEscapeRoomLevel(level);
            dbScene.setEscapeRoomSequenceId(level.getEscapeRoomLevelId());

            // Attach nodes
            if (scene.getNodes() != null && !scene.getNodes().isEmpty()) {
                List<Node> nodes = new ArrayList<>();
                for (NodeDTO node : scene.getNodes()) {
                    Node dbNode = toDBNode(node);
                    dbNode.setScene(dbScene);
                    nodes.add(dbNode);
                }
                dbScene.setNodes(nodes);
            }

            dbScene = sceneRepository.saveAndFlush(dbScene);
            dbScenes.add(dbScene);
        }
        return dbScenes;
    }

    public SceneDTO updateScene(String escapeRoomSceneId, SceneRequestDTO scene) {
        Scene dbScene = sceneRepository.findById(UUID.fromString(escapeRoomSceneId))
                .orElseThrow(() -> new IllegalArgumentException("Scene with id " + escapeRoomSceneId + " not found"));

        copyNonNullProperties(dbScene, scene);
        dbScene = sceneRepository.saveAndFlush(dbScene);
        log.info("Updated scene with sequence {}", dbScene.getSceneSequence());
        return toScene(dbScene);
    }

    public DeleteLevelResponseDTO deleteScene(String id) {
        sceneRepository.deleteById(UUID.fromString(id));
        log.info("Deleted scene with id {}", id);
        return new DeleteLevelResponseDTO("Deleted scene successfully");
    }

    // region Helper Methods
    private SceneRequestDTO convertToSceneRequest(SceneDTO scene, EscapeRoomLevel level) {
        return SceneRequestDTO.builder().sceneSequence(scene.getSceneSequence()).name(scene.getName())
                .backgroundImageUri(scene.getBackgroundImageUri()).nodes(scene.getNodes())
                .escapeRoomLevelId(level.getEscapeRoomLevelId().toString()).build();
    }

    SceneDTO toScene(Scene scene) {
        return SceneDTO.builder().sceneSequence(scene.getSceneSequence()).name(scene.getName())
                .backgroundImageUri(scene.getBackgroundImageURI())
                .nodes(scene.getNodes().stream().map(this::toNode).toList()).build();
    }

    Scene toDBScene(SceneRequestDTO sceneRequest) {
        Scene scene = Scene.builder().name(sceneRequest.getName()).sceneSequence(sceneRequest.getSceneSequence())
                .backgroundImageURI(sceneRequest.getBackgroundImageUri()).nodes(new ArrayList<>()).build();

        if (sceneRequest.getEscapeRoomLevelId() != null) {
            scene.setEscapeRoomLevel(EscapeRoomLevel.builder()
                    .escapeRoomLevelId(UUID.fromString(sceneRequest.getEscapeRoomLevelId())).build());
        }

        if (sceneRequest.getNodes() != null && !sceneRequest.getNodes().isEmpty()) {
            List<Node> nodes = sceneRequest.getNodes().stream().map(this::toDBNode).toList();
            nodes.forEach(node -> node.setScene(scene));
            scene.setNodes(nodes);
        }

        return scene;
    }

    private NodeDTO toNode(Node node) {
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
