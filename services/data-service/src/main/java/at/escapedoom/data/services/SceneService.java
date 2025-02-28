package at.escapedoom.data.services;

import at.escapedoom.data.data.entity.DBNode;
import at.escapedoom.data.data.entity.DBNodeInfo;
import at.escapedoom.data.data.entity.DBPosition;
import at.escapedoom.data.data.entity.DBScene;
import at.escapedoom.data.data.repository.SceneRepository;
import at.escapedoom.data.rest.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static at.escapedoom.data.utils.ReflectionUtils.copyNonNullProperties;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('LECTOR')")
@Slf4j
public class SceneService {

    private final SceneRepository sceneRepository;

    public List<Scene> getAllScenes() {
        log.info("Getting all scenes");
        return sceneRepository.findAll().stream().map(this::toScene).toList();
    }

    public Scene getScene(String id) {
        Optional<DBScene> dbScene = sceneRepository.findById(UUID.fromString(id));
        if (dbScene.isPresent()) {
            log.info("Retrieved scene {}", dbScene);
            return toScene(dbScene.get());
        }
        throw new IllegalArgumentException("Scene with id " + id + " not found");
    }

    public Scene createScene(SceneRequest scene) {
        DBScene dbScene = toDBScene(scene);
        sceneRepository.save(dbScene);

        log.info("Created new scene with sequence {}", dbScene.getSceneSequence());
        return toScene(dbScene);
    }

    public Scene updateScene(String escapeRoomSceneId, SceneRequest scene) {
        Optional<DBScene> dbScene = sceneRepository.findById(UUID.fromString(escapeRoomSceneId));
        if (dbScene.isPresent()) {
            copyNonNullProperties(dbScene.get(), scene);

            log.info("Updating scene with sequence {}", dbScene.get().getSceneSequence());
            return toScene(dbScene.get());
        }
        throw new IllegalArgumentException("Scene with id " + escapeRoomSceneId + " not found");
    }

    public DeleteLevelResponse deleteScene(String id) {
        assert id != null;

        sceneRepository.deleteById(UUID.fromString(id));
        log.info("Deleted scene with id {}", id);

        return new DeleteLevelResponse("Deleted scene successfully");
    }

    private Scene toScene(DBScene dbScene) {
        return Scene.builder().sceneSequence(dbScene.getSceneSequence()).name(dbScene.getName())
                .escapeRoomSequenceId(dbScene.getEscapeRoomSequenceId().toString())
                .backgroundImageUri(dbScene.getBackgroundImageURI().toString())
                .nodes(dbScene.getNodes().stream().map(this::toNode).toList()).build();
    }

    private DBScene toDBScene(SceneRequest sceneRequest) {
        return DBScene.builder().name(sceneRequest.getName()).sceneSequence(sceneRequest.getSceneSequence())
                .backgroundImageURI(URI.create(sceneRequest.getBackgroundImageUri()))
                .nodes(sceneRequest.getNodes().stream().map(this::toDBNode).toList()).build();
    }

    private Node toNode(DBNode node) {
        return Node.builder().nodeInfo(toNodeInfo(node.getNodeInfo())).nodeType(node.getNodeType())
                .position(toPosition(node.getPosition())).build();
    }

    private NodeInfo toNodeInfo(DBNodeInfo nodeInfo) {
        return NodeInfo.builder().description(nodeInfo.getDescription()).title(nodeInfo.getTitle())
                .imageURI(nodeInfo.getImageURI()).build();
    }

    private Position toPosition(DBPosition position) {
        return Position.builder().left(position.getLeftPercentage()).top(position.getTopPercentage()).build();
    }

    private DBNode toDBNode(Node node) {
        return DBNode.builder().nodeType(node.getNodeType()).nodeInfo(toDBNodeInfo(node.getNodeInfo()))
                .position(toDBPosition(node.getPosition())).build();
    }

    private DBNodeInfo toDBNodeInfo(NodeInfo nodeInfo) {
        return DBNodeInfo.builder().description(nodeInfo.getDescription()).title(nodeInfo.getTitle())
                .imageURI(nodeInfo.getImageURI()).build();
    }

    private DBPosition toDBPosition(Position position) {
        return DBPosition.builder().topPercentage(position.getTop()).leftPercentage(position.getLeft()).build();
    }
}
