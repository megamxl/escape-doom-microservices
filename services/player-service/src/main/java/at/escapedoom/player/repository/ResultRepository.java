package at.escapedoom.player.repository;

import at.escapedoom.player.entity.Result;
import at.escapedoom.player.entity.ResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query("SELECT new at.escapedoom.player.entity.ResultDTO(r.escapeRoomLevel, r.input, r.solvedLevelAt, r.awardedPoints) FROM Result r where r.userProgress.userIdentifier = :userIdentifier")
    List<ResultDTO> findResultsByUserIdentifier(@Param("userIdentifier") UUID userIdentifier);

}
