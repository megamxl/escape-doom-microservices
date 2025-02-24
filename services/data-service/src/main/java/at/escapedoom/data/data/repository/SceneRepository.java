package at.escapedoom.data.data.repository;

import at.escapedoom.data.data.entity.DBScene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SceneRepository extends JpaRepository<DBScene, UUID> {

}
