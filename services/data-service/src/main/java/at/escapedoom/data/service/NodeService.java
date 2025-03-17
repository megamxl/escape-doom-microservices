package at.escapedoom.data.service;

import at.escapedoom.data.data.NodeRepository;
import at.escapedoom.data.data.entity.Node;
import at.escapedoom.data.mapper.NodeMapper;
import at.escapedoom.data.rest.model.NodeCreationRequest;
import at.escapedoom.data.rest.model.NodeDTO;
import at.escapedoom.data.rest.model.NodeDeletionResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static at.escapedoom.spring.security.KeycloakUserUtil.getCurrentUserUUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NodeService {

    private final NodeRepository nodeRepository;
    private final NodeMapper nodeMapper;

    @Transactional
    public NodeDTO createNode(NodeCreationRequest creationRequest) {
        assert creationRequest != null;
        assert creationRequest.getSceneId() != null;

        log.debug("Received NodeCreationRequest: {}", creationRequest);

        Node newNode = nodeMapper.toEntity(creationRequest);

        newNode = nodeRepository.saveAndFlush(newNode);

        log.info("Created Node with id: {} for user {}", newNode.getNodeId(), getCurrentUserUUID().get());

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
            throw new NoSuchElementException();
        }

        log.debug("Found NodeId: {}", nodeId);

        return nodeMapper.toDTO(node);
    }

    public NodeDTO updateNode(String nodeId, NodeDTO updatedNode) {
        assert updatedNode != null;
        assert nodeId != null;

        UUID nodeUUID = UUID.fromString(nodeId);

        nodeRepository.findById(nodeUUID)
                .orElseThrow(() -> new NoSuchElementException("Node with id " + nodeUUID + " not found"));

        log.debug("Found node: {} for update request", nodeId);

        Node node = nodeMapper.toEntity(updatedNode);

        log.info("Updated Node with id: {} by {}", node.getNodeId(), getCurrentUserUUID().get());

        return nodeMapper.toDTO(nodeRepository.save(node));
    }

    public NodeDeletionResponseDTO deleteNode(String nodeId) {
        assert nodeId != null : "UUID must not be null";

        UUID nodeUUID = UUID.fromString(nodeId);

        if (nodeRepository.existsById(nodeUUID)) {
            nodeRepository.deleteById(nodeUUID);

            log.info("Deleted node with id " + nodeUUID);
            return new NodeDeletionResponseDTO("Deleted node with id " + nodeUUID);
        }

        throw new NoSuchElementException("Node with id " + nodeUUID + " not found");
    }
}
