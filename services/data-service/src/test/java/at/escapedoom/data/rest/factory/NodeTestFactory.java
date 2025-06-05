package at.escapedoom.data.rest.factory;

import at.escapedoom.data.rest.model.*;

import java.util.Map;
import java.util.UUID;

public class NodeTestFactory {

    public static NodeCreationRequest createRequest(String sceneId) {

        NodeSpecificsDTO.NodeSpecificsDTOBuilder nodeSpecific = NodeSpecificsDTO.builder();
        nodeSpecific.nodeType(NodeType.ZOOM);
        nodeSpecific.additionalProperties(Map.of("return_description", "I am a console node return description",
                "constraints", "Some funny constraints", "example", "Some example"));

        PositionDTO.PositionDTOBuilder position = PositionDTO.builder();
        position.topPercentage(50.0);
        position.leftPercentage(30.0);

        NodeCreationRequest.NodeCreationRequestBuilder request = NodeCreationRequest.builder();
        request.sceneId(sceneId);
        request.title("Test Node");
        request.description("This is a test node");
        request.nodeSpecifics(nodeSpecific.build());
        request.position(position.build());

        return request.build();
    }

    public static NodeDTO createResponseFrom(NodeCreationRequest request) {
        NodeDTO.NodeDTOBuilder dto = NodeDTO.builder();
        dto.nodeId(UUID.fromString(UUID.randomUUID().toString()));
        dto.sceneId(UUID.fromString(request.getSceneId()));
        dto.title(request.getTitle());
        dto.description(request.getDescription());
        dto.position(request.getPosition());
        dto.nodeSpecifics(request.getNodeSpecifics());
        return dto.build();
    }
}
