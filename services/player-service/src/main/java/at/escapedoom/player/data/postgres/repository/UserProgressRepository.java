package at.escapedoom.player.data.postgres.repository;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, UUID> {

    @Query("SELECT count(u) FROM UserProgress u where u.roomPin = :roomPin and u.currentEscapeRoomLevel >= :levelToCheck and u.lastRiddleSolvedAt is not null")
    Optional<Integer> getAmountOfUsersSolvedThisLevelByRoomPin(@Param("roomPin") Long roomPin,
            @Param("levelToCheck") Long levelToCheck);

    List<UserProgress> getUserNamesByRoomPin(Long roomPin);

    @Query("SELECT u FROM UserProgress u where u.userIdentifier = :userId")
    Optional<UserProgress> getUserProgressByUserId(@Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query("UPDATE UserProgress u SET u.lastRiddleSolvedAt = CURRENT_TIMESTAMP, u.currentPoints = u.currentPoints + :points WHERE u.userIdentifier = :userId")
    int updateUserProgress(UUID userId, Long points);

    @Modifying
    @Transactional
    @Query("UPDATE UserProgress u SET u.currentEscapeRoomLevel = u.currentEscapeRoomLevel + 1 WHERE u.userIdentifier = :userId")
    int updateUserLvl(UUID userId);

}
