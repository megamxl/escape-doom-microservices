package at.escapedoom.data.services;

import at.escapedoom.data.data.repository.SceneRepository;
import at.escapedoom.data.rest.model.Scene;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('LECTOR')")
public class SceneService {

    private final SceneRepository sceneRepository;

    public Scene createScene(Scene scene) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
