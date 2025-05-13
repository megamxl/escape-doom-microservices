package at.escapedoom.data.service.rest.factory;

import at.escapedoom.data.rest.model.*;

import java.util.Map;
import java.util.UUID;

public class NodeTestFactory {

    public static NodeCreationRequest createRequest(String sceneId) {

        NodeSpecificsDTO nodeSpecific = new NodeSpecificsDTO();
        nodeSpecific.setNodeType(NodeType.ZOOM);
        nodeSpecific.setAdditionalProperties(Map.of("return_description", "I am a console node return description",
                "constraints", "Some funny constraints", "example", "Some example"));

        PositionDTO position = new PositionDTO();
        position.setTopPercentage(50.0);
        position.setLeftPercentage(30.0);

        NodeCreationRequest request = new NodeCreationRequest();
        request.setSceneId(sceneId);
        request.setTitle("Test Node");
        request.setDescription("This is a test node");
        request.setNodeSpecifics(nodeSpecific);
        request.setPosition(position);

        return request;
    }

    public static NodeDTO createResponseFrom(NodeCreationRequest request) {
        NodeDTO dto = new NodeDTO();
        dto.setNodeId(UUID.fromString(UUID.randomUUID().toString()));
        dto.setSceneId(UUID.fromString(request.getSceneId()));
        dto.setTitle(request.getTitle());
        dto.setDescription(request.getDescription());
        dto.setPosition(request.getPosition());
        dto.setNodeSpecifics(request.getNodeSpecifics());
        return dto;
    }
}
