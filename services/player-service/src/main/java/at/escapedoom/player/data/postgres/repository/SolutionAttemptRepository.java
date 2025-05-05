package at.escapedoom.player.data.postgres.repository;

import at.escapedoom.player.data.postgres.entity.SolutionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SolutionAttemptRepository extends JpaRepository<SolutionAttempt, Long> {

    Optional<SolutionAttempt> findByPlayerUUID(UUID playerUUID);
}
