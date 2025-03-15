package at.escapedoom.data.service.delegate;

import at.escapedoom.data.rest.api.NodeApiDelegate;
import at.escapedoom.data.rest.model.NodeCreationRequest;
import at.escapedoom.data.rest.model.NodeDTO;
import at.escapedoom.data.rest.model.NodeDeletionResponseDTO;
import at.escapedoom.data.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@PreAuthorize("hasRole('LECTOR')")
public class NodeApiDelegateImpl implements NodeApiDelegate {

    private final NodeService nodeService;

    @Override
    public ResponseEntity<NodeDTO> createNode(NodeCreationRequest nodeCreationRequest) {
        return new ResponseEntity<>(nodeService.createNode(nodeCreationRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<NodeDeletionResponseDTO> deleteNode(String escapeRoomNodeId) {
        return new ResponseEntity<>(nodeService.deleteNode(escapeRoomNodeId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NodeDTO>> getAllNodes() {
        return new ResponseEntity<>(nodeService.getAllNodes(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NodeDTO> getNode(String escapeRoomNodeId) {
        return new ResponseEntity<>(nodeService.getNodeById(escapeRoomNodeId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NodeDTO> updateNode(String escapeRoomNodeId, NodeDTO nodeCreationRequest) {
        return new ResponseEntity<>(nodeService.updateNode(escapeRoomNodeId, nodeCreationRequest), HttpStatus.OK);
    }
}
