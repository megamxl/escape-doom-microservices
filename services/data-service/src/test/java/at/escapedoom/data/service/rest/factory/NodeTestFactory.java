package at.escapedoom.data.service.rest.factory;

import at.escapedoom.data.rest.model.*;

import java.util.UUID;

public class NodeTestFactory {

    public static NodeCreationRequest createRequest(String sceneId) {
        NodeInfoDTO nodeInfo = new NodeInfoDTO();
        nodeInfo.setTitle("Test Node");
        nodeInfo.setDescription("This is a test node");
        nodeInfo.setImageURI("https://example.com/image.png");

        PositionDTO position = new PositionDTO();
        position.setTopPercentage(50.0);
        position.setLeftPercentage(30.0);

        NodeCreationRequest request = new NodeCreationRequest();
        request.setSceneId(sceneId);
        request.setNodeType(NodeType.ZOOM);
        request.setNodeInfo(nodeInfo);
        request.setPosition(position);

        return request;
    }

    public static NodeDTO createResponseFrom(NodeCreationRequest request) {
        NodeDTO dto = new NodeDTO();
        dto.setNodeId(UUID.fromString(UUID.randomUUID().toString()));
        dto.setSceneId(UUID.fromString(request.getSceneId()));
        dto.setNodeType(request.getNodeType());
        dto.setNodeInfo(request.getNodeInfo());
        dto.setPosition(request.getPosition());
        return dto;
    }
}
