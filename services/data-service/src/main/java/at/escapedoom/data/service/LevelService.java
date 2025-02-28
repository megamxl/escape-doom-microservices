package at.escapedoom.data.service;

import at.escapedoom.data.data.entity.EscapeRoomLevel;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.data.reporitory.EscapeRoomLevelRepository;
import at.escapedoom.data.rest.model.DeleteLevelSuccess;
import at.escapedoom.data.rest.model.NodeType;
import at.escapedoom.data.rest.model.Riddle;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class LevelService {

    private final EscapeRoomLevelRepository repository;

    public LevelService(EscapeRoomLevelRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public at.escapedoom.data.rest.model.EscapeRoomLevel saveLevel(at.escapedoom.data.rest.model.EscapeRoomLevel level) {
        EscapeRoomLevel entity;

        // If the level already exists, fetch it; otherwise, create a new one
        if (level.getEscapeRoomLevelId() != null) {
            entity = repository.findById(UUID.fromString(level.getEscapeRoomLevelId()))
                    .orElseThrow(() -> new EntityNotFoundException("Level not found"));
        } else {
            entity = new EscapeRoomLevel();
            entity.setEscapeRoomLevelId(UUID.randomUUID());
        }

        // Convert DTO fields to entity fields
        entity.setLevelSequence(level.getSequence().intValue());

        // Convert DTO List<Scene> to entity List<Scene>
        entity.setScenes(level.getScenes().stream().map(scene -> {
            Scene entityScene = new Scene();
            entityScene.setEscapeRoomSequenceId(UUID.fromString(scene.getEscapeRoomSequenceId()));
            entityScene.setName(scene.getName());
            entityScene.setBackgroundImageURI(URI.create(scene.getBackgroundImageUri()));
            return entityScene;
        }).toList());

        // Convert DTO Riddle to entity Riddle (if exists)
        if (level.getRiddles() != null) {
            at.escapedoom.data.data.entity.Riddle entityRiddle = new at.escapedoom.data.data.entity.Riddle();

            entity.setRiddle(entityRiddle);
        }

        // Save the updated entity
        EscapeRoomLevel savedEntity = repository.save(entity);

        // Convert back from entity to DTO and return
        return  null;

    }

    public DeleteLevelSuccess deleteLevel(String escapeRoomLevelId) {
        UUID id = validateUUID(escapeRoomLevelId);

        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Level not found");
        }
        repository.deleteById(id);

        return DeleteLevelSuccess.builder().code(BigDecimal.valueOf(HttpStatus.OK.value()))
                .message("Level deleted successfully").build();
    }

    public List<at.escapedoom.data.rest.model.EscapeRoomLevel> getAllLevels() {
        return repository.findAll().stream().map(entity -> {
            at.escapedoom.data.rest.model.EscapeRoomLevel apiModel = new at.escapedoom.data.rest.model.EscapeRoomLevel();
            apiModel.setEscapeRoomLevelId(entity.getEscapeRoomLevelId().toString());
            apiModel.setSequence(BigDecimal.valueOf(entity.getLevelSequence()));

            // Convert entity scenes to API model scenes
            apiModel.setScenes(entity.getScenes().stream().map(scene -> {
                at.escapedoom.data.rest.model.Scene apiScene = new at.escapedoom.data.rest.model.Scene();
                apiScene.setEscapeRoomSequenceId(scene.getEscapeRoomSequenceId().toString());
                apiScene.setName(scene.getName());
                apiScene.setBackgroundImageUri(scene.getBackgroundImageURI().toString());

                List<at.escapedoom.data.rest.model.Node> apiNodes = scene.getNodes().stream().map(node -> {
                    at.escapedoom.data.rest.model.Node apiNode = new at.escapedoom.data.rest.model.Node();
                    apiNode.setNodeType(NodeType.valueOf(node.getNodeType().name())); // Convert enum to API model enum
                    apiNode.setPosition(new at.escapedoom.data.rest.model.Position(
                            node.getPosition().getTopPercentage(), node.getPosition().getLeftPercentage()));
                    apiNode.setNodeInfo(new at.escapedoom.data.rest.model.NodeInfo(node.getNodeInfo().getImageURI(),
                            node.getNodeInfo().getTitle(), node.getNodeInfo().getDescription()));
                    return apiNode;
                }).toList(); // Convert to list of API nodes

                apiScene.setNodes(apiNodes); // Set nodes in scene
                return apiScene;
            }).toList() // Convert to list of API scenes
            );

            // Convert the entity's riddle to the API model's riddle
            /*
             * apiModel.setRiddles(entity.getRiddle() != null ? List.of(new at.escapedoom.data.rest.model.Riddle(
             * entity.getRiddle().getEscapeRoomRiddleId().toString(), entity.getRiddle().getExpectedOutput(),
             * enums.RiddleType.valueOf(entity.getRiddle().getType().name()))) : null);
             *
             */

            return apiModel;
        }).toList();
    }

    public at.escapedoom.data.rest.model.EscapeRoomLevel getLevel(String escapeRoomLevelId) {
        UUID id = validateUUID(escapeRoomLevelId);

        EscapeRoomLevel entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Level not found"));

        at.escapedoom.data.rest.model.EscapeRoomLevel apiModel = new at.escapedoom.data.rest.model.EscapeRoomLevel();
        apiModel.setEscapeRoomLevelId(entity.getEscapeRoomLevelId().toString());
        apiModel.setSequence(BigDecimal.valueOf(entity.getLevelSequence()));
        apiModel.setScenes(entity.getScenes().stream().map(scene -> {
            at.escapedoom.data.rest.model.Scene apiScene = new at.escapedoom.data.rest.model.Scene();
            apiScene.setEscapeRoomSequenceId(scene.getEscapeRoomSequenceId().toString());
            apiScene.setName(scene.getName());
            apiScene.setBackgroundImageUri(scene.getBackgroundImageURI().toString());

            List<at.escapedoom.data.rest.model.Node> apiNodes = scene.getNodes().stream().map(node -> {
                at.escapedoom.data.rest.model.Node apiNode = new at.escapedoom.data.rest.model.Node();
                apiNode.setNodeType(NodeType.valueOf(node.getNodeType().name()));
                apiNode.setPosition(new at.escapedoom.data.rest.model.Position(node.getPosition().getTopPercentage(),
                        node.getPosition().getLeftPercentage()));
                apiNode.setNodeInfo(new at.escapedoom.data.rest.model.NodeInfo(node.getNodeInfo().getImageURI(),
                        node.getNodeInfo().getTitle(), node.getNodeInfo().getDescription()));
                return apiNode;
            }).toList();

            apiScene.setNodes(apiNodes);
            return apiScene;
        }).toList());

        /*
         * apiModel.setRiddles(entity.getRiddle() != null ? List .of(new
         * at.escapedoom.data.rest.model.Riddle(entity.getRiddle().getEscapeRoomRiddleId().toString(),
         * entity.getRiddle().getExpectedOutput(), enums.valueOf(entity.getRiddle().getType().name()))) : null);
         *
         */

        return apiModel;
    }

    private UUID validateUUID(String escapeRoomLevelId) {
        try {
            return UUID.fromString(escapeRoomLevelId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format");
        }
    }

    public at.escapedoom.data.rest.model.EscapeRoomLevel updateLevel(String escapeRoomLevelId,
            at.escapedoom.data.rest.model.EscapeRoomLevel escapeRoomLevelApi) {
        UUID id = validateUUID(escapeRoomLevelId);

        EscapeRoomLevel existingLevel = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Level not found"));

        // Update scenes
        existingLevel.setScenes(escapeRoomLevelApi.getScenes().stream().map(scene -> {
            at.escapedoom.data.data.entity.Scene entityScene = new at.escapedoom.data.data.entity.Scene();
            entityScene.setEscapeRoomSequenceId(UUID.fromString(scene.getEscapeRoomSequenceId()));
            entityScene.setName(scene.getName());
            entityScene.setBackgroundImageURI(URI.create(scene.getBackgroundImageUri()));

            List<at.escapedoom.data.data.entity.Node> entityNodes = scene.getNodes().stream().map(node -> {
                at.escapedoom.data.data.entity.Node entityNode = new at.escapedoom.data.data.entity.Node();

                at.escapedoom.data.data.entity.Position entityPosition = new at.escapedoom.data.data.entity.Position();
                entityPosition.setTopPercentage(node.getPosition().getTop());
                entityPosition.setLeftPercentage(node.getPosition().getLeft());
                entityNode.setPosition(entityPosition);

                at.escapedoom.data.data.entity.NodeInfo entityNodeInfo = new at.escapedoom.data.data.entity.NodeInfo();
                entityNodeInfo.setImageURI(node.getNodeInfo().getImageURI());
                entityNodeInfo.setTitle(node.getNodeInfo().getTitle());
                entityNodeInfo.setDescription(node.getNodeInfo().getDescription());
                entityNode.setNodeInfo(entityNodeInfo);

                entityNode.setNodeType(enums.NodeType.valueOf(node.getNodeType().name()));
                return entityNode;
            }).toList();

            entityScene.setNodes(entityNodes);
            return entityScene;
        }).toList());

        /*
         * existingLevel .setRiddle( escapeRoomLevelApi.getRiddles() != null &&
         * !escapeRoomLevelApi.getRiddles().isEmpty() ? new at.escapedoom.data.data.entity.Riddle(
         * UUID.fromString(escapeRoomLevelApi.getRiddles().get(0).getEscapeRoomRiddleId()),
         * escapeRoomLevelApi.getRiddles().get(0).getExpectedOutput(), enums.RiddleType
         * .valueOf(escapeRoomLevelApi.getRiddles().get(0).getType().name())) : null);
         *
         */

        // Update level sequence
        existingLevel.setLevelSequence(escapeRoomLevelApi.getSequence().intValue());

        // Save updated level to the repository
        repository.save(existingLevel);

        // Convert updated entity back to the API model
        at.escapedoom.data.rest.model.EscapeRoomLevel apiModel = new at.escapedoom.data.rest.model.EscapeRoomLevel();
        apiModel.setEscapeRoomLevelId(existingLevel.getEscapeRoomLevelId().toString());
        apiModel.setSequence(BigDecimal.valueOf(existingLevel.getLevelSequence()));
        apiModel.setScenes(existingLevel.getScenes().stream().map(scene -> {
            at.escapedoom.data.rest.model.Scene apiScene = new at.escapedoom.data.rest.model.Scene();
            apiScene.setEscapeRoomSequenceId(scene.getEscapeRoomSequenceId().toString());
            apiScene.setName(scene.getName());
            apiScene.setBackgroundImageUri(scene.getBackgroundImageURI().toString());

            List<at.escapedoom.data.rest.model.Node> apiNodes = scene.getNodes().stream().map(node -> {
                at.escapedoom.data.rest.model.Node apiNode = new at.escapedoom.data.rest.model.Node();
                apiNode.setNodeType(NodeType.valueOf(node.getNodeType().name())); // Convert enum to API model enum
                apiNode.setPosition(new at.escapedoom.data.rest.model.Position(node.getPosition().getTopPercentage(),
                        node.getPosition().getLeftPercentage()));
                apiNode.setNodeInfo(new at.escapedoom.data.rest.model.NodeInfo(node.getNodeInfo().getImageURI(),
                        node.getNodeInfo().getTitle(), node.getNodeInfo().getDescription()));
                return apiNode;
            }).toList();

            apiScene.setNodes(apiNodes);
            return apiScene;
        }).toList());
        /*
         * apiModel.setRiddles(existingLevel.getRiddle() != null ? List.of( new
         * at.escapedoom.data.rest.model.Riddle(existingLevel.getRiddle().getEscapeRoomRiddleId().toString(),
         * existingLevel.getRiddle().getExpectedOutput(),
         * enums.RiddleType.valueOf(existingLevel.getRiddle().getType().name()))) : null);
         *
         */

        return apiModel;
    }

}
