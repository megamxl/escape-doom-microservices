package at.escapedoom.session.data.repository;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EscapeRoomSessionRepository extends JpaRepository<EscapeRoomSession, UUID> {
    List<EscapeRoomSession> getEscapeRoomSessionsByUserIdOrderByStartTimeDesc(UUID userId);

    Optional<EscapeRoomSession> getEscapeRoomSessionByRoomPin(Long roomPin);

    List<EscapeRoomSession> getEscapeRoomSessionsByUserIdAndTagsContains(UUID userId, List<String> tags);
}
