package at.escapedoom.spring.redis.data.repositories;

import at.escapedoom.spring.redis.data.models.SessionView;
import org.springframework.data.repository.CrudRepository;

public interface SessionViewRepository extends CrudRepository<SessionView, Long> {
}
