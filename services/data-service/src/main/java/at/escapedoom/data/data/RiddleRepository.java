package at.escapedoom.data.data;

import at.escapedoom.data.data.entity.Riddle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RiddleRepository extends JpaRepository<Riddle, UUID> {
}
