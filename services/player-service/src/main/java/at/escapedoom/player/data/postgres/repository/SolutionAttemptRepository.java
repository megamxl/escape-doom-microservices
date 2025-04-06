package at.escapedoom.player.data.postgres.repository;

import at.escapedoom.player.data.postgres.entity.SolutionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionAttemptRepository extends JpaRepository<SolutionAttempt, Long> {

}
