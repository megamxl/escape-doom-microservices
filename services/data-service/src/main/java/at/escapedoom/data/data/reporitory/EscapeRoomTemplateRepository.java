package at.escapedoom.data.data.reporitory;

import at.escapedoom.data.data.entity.EscapeRoomTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EscapeRoomTemplateRepository extends JpaRepository<EscapeRoomTemplate, UUID> {
}
