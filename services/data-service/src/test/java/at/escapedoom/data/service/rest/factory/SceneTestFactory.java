package at.escapedoom.data.service.rest.factory;

import at.escapedoom.data.rest.model.*;

import java.util.UUID;

public class SceneTestFactory {

    public static SceneRequestDTO createRequest(String levelId) {
        SceneRequestDTO request = new SceneRequestDTO();
        request.setName("Test Scene");
        request.setLevelId(levelId);
        request.setSceneSequence(1);
        request.setBackgroundImageUri("https://example.com/background.png");
        return request;
    }

    public static SceneDTO createResponseFrom(SceneRequestDTO request) {
        SceneDTO response = new SceneDTO();
        response.setSceneId(UUID.randomUUID().toString());
        response.setLevelId(request.getLevelId());
        response.setSceneSequence(request.getSceneSequence());
        response.setBackgroundImageUri(request.getBackgroundImageUri());
        response.setName(request.getName());
        response.setNodes(java.util.Collections.emptyList());
        return response;
    }
}
