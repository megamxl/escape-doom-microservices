package at.escapedoom.data.data;

import at.escapedoom.data.data.entity.Scene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SceneRepository extends JpaRepository<Scene, UUID> {

    boolean existsByLevelIdAndSceneSequence(UUID levelId, Integer sceneSequence);
}
