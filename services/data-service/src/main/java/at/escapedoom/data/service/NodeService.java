package at.escapedoom.data.service;

import at.escapedoom.data.data.NodeRepository;
import at.escapedoom.data.data.entity.Node;
import at.escapedoom.data.mapper.NodeMapper;
import at.escapedoom.data.rest.model.NodeCreationRequest;
import at.escapedoom.data.rest.model.NodeDTO;
import at.escapedoom.data.rest.model.NodeDeletionResponseDTO;
import at.escapedoom.data.rest.model.RiddleDeletionResponseDTO;
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

    @Transactional
    public NodeDTO createNode(NodeCreationRequest creationRequest) {
        assert creationRequest != null;
        assert creationRequest.getSceneId() != null;

        Node newNode = nodeMapper.toEntity(creationRequest);

        newNode = nodeRepository.saveAndFlush(newNode);

        return nodeMapper.toDTO(newNode);
    }

    public List<NodeDTO> getAllNodes() {
        List<Node> nodes = nodeRepository.findAll();
        return nodes.stream().map(nodeMapper::toDTO).toList();
    }

    public NodeDTO getNodeById(String nodeId) {
        assert nodeId != null;

        return nodeMapper.toDTO(nodeRepository.findById(UUID.fromString(nodeId)).orElse(null));
    }

    public NodeDTO updateNode(String nodeId, NodeDTO updatedNode) {
        assert updatedNode != null;
        assert nodeId != null;

        UUID nodeUUID = UUID.fromString(nodeId);

        nodeRepository.findById(nodeUUID)
                .orElseThrow(() -> new NoSuchElementException("Node with id " + nodeUUID + " not found"));

        Node node = nodeMapper.toEntity(updatedNode);

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
