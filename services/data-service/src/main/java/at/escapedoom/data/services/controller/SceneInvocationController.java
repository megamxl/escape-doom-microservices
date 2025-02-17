package at.escapedoom.data.services.controller;

import at.escapedoom.data.rest.api.SceneApiDelegate;
import at.escapedoom.data.rest.model.DeleteLevelResponse;
import at.escapedoom.data.rest.model.Scene;
import at.escapedoom.data.services.SceneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SceneInvocationController implements SceneApiDelegate {

    private final SceneService sceneService;

    @Override
    public ResponseEntity<Scene> createScene(Scene scene) {
        var ret = sceneService.createScene(scene);
        return ResponseEntity.ok(ret);

    }

    @Override
    public ResponseEntity<DeleteLevelResponse> deleteScene(String escapeRoomSceneId) {
        return SceneApiDelegate.super.deleteScene(escapeRoomSceneId);
    }

    @Override
    public ResponseEntity<List<Scene>> getAllScenes() {
        return SceneApiDelegate.super.getAllScenes();
    }

    @Override
    public ResponseEntity<Scene> getScene(String escapeRoomSceneId) {
        return SceneApiDelegate.super.getScene(escapeRoomSceneId);
    }

    @Override
    public ResponseEntity<Scene> putScene(String escapeRoomSceneId, Scene scene) {
        return SceneApiDelegate.super.putScene(escapeRoomSceneId, scene);
    }
}
