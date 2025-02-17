package at.escapedoom.data.data.repository;

import at.escapedoom.data.data.entity.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SceneRepository extends JpaRepository<Scene, UUID> {

}
