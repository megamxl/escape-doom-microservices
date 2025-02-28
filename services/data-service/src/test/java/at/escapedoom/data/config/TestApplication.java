package at.escapedoom.data.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "at.escapedoom.data", excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = JpaConfig.class))

@EntityScan(basePackages = "at.escapedoom.data")
@EnableJpaRepositories(basePackages = "at.escapedoom.data")
public class TestApplication {
}
