package at.escapedoom.data.data;

import at.escapedoom.data.data.entity.riddle.Riddle;
import at.escapedoom.data.data.entity.riddle.RiddleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RiddleRepository extends JpaRepository<Riddle, UUID> {
}
