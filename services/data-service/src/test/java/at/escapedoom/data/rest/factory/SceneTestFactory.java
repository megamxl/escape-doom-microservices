package at.escapedoom.data.rest.factory;

import at.escapedoom.data.rest.model.SceneDTO;
import at.escapedoom.data.rest.model.SceneRequestDTO;

import java.util.UUID;

public class SceneTestFactory {

    public static SceneRequestDTO createRequest(String levelId) {
        SceneRequestDTO.SceneRequestDTOBuilder builder = SceneRequestDTO.builder();
        builder.name("Test Scene");
        builder.levelId(levelId);
        builder.sceneSequence(1);
        builder.backgroundImageUri("https://example.com/background.png");
        return builder.build();
    }

    public static SceneDTO createResponseFrom(SceneRequestDTO request) {
        SceneDTO.SceneDTOBuilder response = SceneDTO.builder();
        response.sceneId(UUID.randomUUID().toString());
        response.levelId(request.getLevelId());
        response.sceneSequence(request.getSceneSequence());
        response.backgroundImageUri(request.getBackgroundImageUri());
        response.name(request.getName());
        response.nodes(java.util.Collections.emptyList());
        return response.build();
    }
}
