package at.escapedoom.data.data.reporitory;

import at.escapedoom.data.data.entity.EscapeRoomLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EscapeRoomLevelRepository extends JpaRepository<EscapeRoomLevel, UUID> {
}
