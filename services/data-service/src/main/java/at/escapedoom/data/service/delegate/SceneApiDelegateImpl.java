package at.escapedoom.data.service.delegate;

import at.escapedoom.data.rest.api.SceneApiDelegate;
import at.escapedoom.data.rest.model.DeleteLevelResponseDTO;
import at.escapedoom.data.rest.model.SceneDTO;
import at.escapedoom.data.rest.model.SceneRequestDTO;
import at.escapedoom.data.service.SceneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SceneApiDelegateImpl implements SceneApiDelegate {

    private final SceneService sceneService;

    @Override
    public ResponseEntity<List<SceneDTO>> getAllScenes() {
        return new ResponseEntity<>(sceneService.getAllScenes(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SceneDTO> createScene(SceneRequestDTO scene) {
        return new ResponseEntity<>(sceneService.createScene(scene), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeleteLevelResponseDTO> deleteScene(String escapeRoomSceneId) {
        return new ResponseEntity<>(sceneService.deleteScene(escapeRoomSceneId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SceneDTO> getSceneById(String escapeRoomSceneId) {
        return new ResponseEntity<>(sceneService.getSceneById(escapeRoomSceneId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SceneDTO> updateScene(String escapeRoomSceneId, SceneRequestDTO scene) {
        return new ResponseEntity<>(sceneService.updateScene(escapeRoomSceneId, scene), HttpStatus.OK);
    }
}
