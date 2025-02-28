package at.escapedoom.data.data.repository;

import at.escapedoom.data.data.entity.DBRiddle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RiddleRepository extends JpaRepository<DBRiddle, UUID> {

}
