package at.escapedoom.player.data.postgres.repository;

import at.escapedoom.player.data.postgres.entity.SolutionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SolutionAttemptRepository extends JpaRepository<SolutionAttempt, Long> {

    @Query("SELECT s FROM SolutionAttempt s WHERE s.playerUUID = :playerUUID AND s.status != at.escapedoom.player.rest.model.EscapeRoomResult.StatusEnum.WAITING ")
    Optional<SolutionAttempt> findByPlayerUUID(UUID playerUUID);
}
