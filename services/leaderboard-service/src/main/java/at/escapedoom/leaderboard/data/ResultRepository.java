package at.escapedoom.leaderboard.data;

import at.escapedoom.leaderboard.data.entity.Result;
import at.escapedoom.leaderboard.data.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<List<Result>> getResultsByUserProgress_UserIdentifier(UUID userIdentifier);
}
