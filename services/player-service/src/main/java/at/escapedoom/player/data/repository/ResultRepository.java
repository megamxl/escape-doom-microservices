package at.escapedoom.player.data.repository;

import at.escapedoom.player.data.entity.Result;
import at.escapedoom.player.data.entity.ResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query("SELECT new at.escapedoom.player.data.entity.ResultDTO(r.escapeRoomLevel, r.input, r.solvedLevelAt, r.awardedPoints) FROM Result r where r.userProgress.userIdentifier = :userIdentifier")
    Optional<List<ResultDTO>> findResultsByUserIdentifier(@Param("userIdentifier") UUID userIdentifier);

}
