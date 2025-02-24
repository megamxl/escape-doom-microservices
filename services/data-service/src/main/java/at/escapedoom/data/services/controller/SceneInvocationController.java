package at.escapedoom.data.services.controller;

import at.escapedoom.data.rest.api.SceneApiDelegate;
import at.escapedoom.data.rest.model.DeleteLevelResponse;
import at.escapedoom.data.rest.model.Scene;
import at.escapedoom.data.rest.model.SceneRequest;
import at.escapedoom.data.services.SceneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SceneInvocationController implements SceneApiDelegate {

    private final SceneService sceneService;

    @Override
    public ResponseEntity<List<Scene>> getAllScenes() {
        return new ResponseEntity<>(sceneService.getAllScenes(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Scene> createScene(SceneRequest scene) {
        return new ResponseEntity<>(sceneService.createScene(scene), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeleteLevelResponse> deleteScene(String escapeRoomSceneId) {
        return new ResponseEntity<>(sceneService.deleteScene(escapeRoomSceneId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Scene> getScene(String escapeRoomSceneId) {
        return new ResponseEntity<>(sceneService.getScene(escapeRoomSceneId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Scene> putScene(String escapeRoomSceneId, SceneRequest scene) {
        return new ResponseEntity<>(sceneService.updateScene(escapeRoomSceneId, scene), HttpStatus.OK);
    }
}
