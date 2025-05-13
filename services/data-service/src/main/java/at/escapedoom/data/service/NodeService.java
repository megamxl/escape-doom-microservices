package at.escapedoom.data.service;

import at.escapedoom.data.data.NodeRepository;
import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.entity.*;
import at.escapedoom.data.mapper.NodeMapper;
import at.escapedoom.data.rest.model.NodeCreationRequest;
import at.escapedoom.data.rest.model.NodeDTO;
import at.escapedoom.data.rest.model.NodeDeletionResponseDTO;
import at.escapedoom.data.rest.model.NodeSpecificsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NodeService {

    private final NodeRepository nodeRepository;
    private final NodeMapper nodeMapper;
    private final SceneRepository sceneRepository;

    @Transactional
    public NodeDTO createNode(NodeCreationRequest creationRequest) {
        assert creationRequest != null;
        assert creationRequest.getSceneId() != null;

        log.debug("Received NodeCreationRequest: {}", creationRequest);

        Node newNode = nodeMapper.toEntity(creationRequest);
        newNode.setNodeSpecifics(castNodeSpecifics(creationRequest));

        newNode = nodeRepository.saveAndFlush(newNode);

        log.info("Created Node with id: {}", newNode.getNodeId());

        return nodeMapper.toDTO(newNode);
    }

    public List<NodeDTO> getAllNodes() {
        List<Node> nodes = nodeRepository.findAll();

        log.debug("Found: {} nodes", nodes.size());

        return nodes.stream().map(nodeMapper::toDTO).toList();
    }

    public NodeDTO getNodeById(String nodeId) {
        assert nodeId != null;

        var node = nodeRepository.findById(UUID.fromString(nodeId)).orElse(null);
        if (node == null) {
            throw new NoSuchElementException("Node with ID: " + nodeId + " not found");
        }

        log.debug("Found NodeId: {}", nodeId);

        return nodeMapper.toDTO(node);
    }

    @Transactional
    public NodeDTO updateNode(String nodeId, NodeDTO updatedNode) {
        assert updatedNode != null : "Updated node is null";
        assert nodeId != null : "NodeId is null";

        UUID nodeUUID = UUID.fromString(nodeId);

        nodeRepository.findById(nodeUUID)
                .orElseThrow(() -> new NoSuchElementException("Node with id " + nodeUUID + " not found"));

        log.debug("Found node: {} for update request", nodeId);

        Node node = nodeMapper.toEntity(updatedNode);
        node.setNodeSpecifics(castNodeSpecifics(updatedNode));

        // log.info("Updated Node with id: {} by {}", node.getNodeId(), getCurrentUserUUID().get());
        log.info("Updated Node with id: {}", node.getNodeId());

        return nodeMapper.toDTO(nodeRepository.save(node));
    }

    @Transactional
    public NodeDeletionResponseDTO deleteNode(String nodeId) {
        assert nodeId != null : "UUID must not be null";

        UUID nodeUUID = UUID.fromString(nodeId);

        Node node = nodeRepository.findById(nodeUUID)
                .orElseThrow(() -> new NoSuchElementException("Node with id " + nodeUUID + " not found"));

        Scene scene = node.getScene();
        if (scene != null) {
            scene.getNodes().remove(node);
            sceneRepository.saveAndFlush(scene);
        } else {
            nodeRepository.deleteById(nodeUUID);
        }

        log.info("Deleted node with id " + nodeUUID);
        return new NodeDeletionResponseDTO("Deleted node with id " + nodeUUID);
    }

    private NodeSpecifics castNodeSpecifics(NodeDTO node) {
        Class<? extends NodeSpecifics> clazz;
        switch (node.getNodeSpecifics().getNodeType()) {
        case CONSOLE -> clazz = ConsoleNodeSpecifics.class;
        case DETAIL -> clazz = DetailsNodeSpecifics.class;
        case ZOOM -> clazz = ZoomNodeSpecifics.class;
        default -> throw new IllegalArgumentException(
                "Unsupported node type: " + node.getNodeSpecifics().getNodeType());
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(node.getNodeSpecifics(), clazz);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Unable to convert node to JSON", e);
        }
    }

    private NodeSpecifics castNodeSpecifics(NodeCreationRequest creationRequest) {
        Class<? extends NodeSpecifics> clazz;
        switch (creationRequest.getNodeSpecifics().getNodeType()) {
        case CONSOLE -> clazz = ConsoleNodeSpecifics.class;
        case DETAIL -> clazz = DetailsNodeSpecifics.class;
        case ZOOM -> clazz = ZoomNodeSpecifics.class;
        default -> throw new IllegalArgumentException(
                "Unsupported node type: " + creationRequest.getNodeSpecifics().getNodeType());
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(creationRequest.getNodeSpecifics(), clazz);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Unable to convert node to JSON", e);
        }
    }
}