package at.escapedoom.data.data;

import at.escapedoom.data.data.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface TemplateRepository extends JpaRepository<Template, UUID> {

    Set<Template> findAllByUserId(UUID userId);
}
