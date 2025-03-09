package at.escapedoom.session.data;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EscapeRoomSessionRepository extends JpaRepository<EscapeRoomSession, UUID> {
    List<EscapeRoomSession> getEscapeRoomSessionsByUserIdOrderByStartTimeDesc(UUID userId);

    Optional<EscapeRoomSession> getEscapeRoomSessionByRoomPin(Long roomPin);

    List<EscapeRoomSession> getEscapeRoomSessionsByUserIdAndTagsContains(UUID userId, List<String> tags);
}
