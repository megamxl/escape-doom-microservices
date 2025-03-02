package at.escapedoom.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "at.escapedoom.data")
@EntityScan(basePackages = "at.escapedoom.data")
public class JpaConfig {
}
