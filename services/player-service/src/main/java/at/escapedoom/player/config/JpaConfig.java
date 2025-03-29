package at.escapedoom.player.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "at.escapedoom.player.data.postgres.repository")
@EntityScan(basePackages = "at.escapedoom.player.data.postgres.entity")
public class JpaConfig {
}