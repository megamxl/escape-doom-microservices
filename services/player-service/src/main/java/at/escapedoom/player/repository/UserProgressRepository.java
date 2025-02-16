package at.escapedoom.player.repository;

import at.escapedoom.player.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, UUID> {

    @Query("SELECT count(u) FROM UserProgress u where u.roomPin = :roomPin and u.currentEscapeRoomLevel >= :levelToCheck and u.lastRiddleSolvedAt is not null")
    Optional<Integer> getAmountOfUsersSolvedThisLevelByRoomPin(@Param("roomPin") Long roomPin,
            @Param("levelToCheck") Long levelToCheck);

}
