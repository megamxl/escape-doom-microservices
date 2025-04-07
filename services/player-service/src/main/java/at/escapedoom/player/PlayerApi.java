package at.escapedoom.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@EnableScheduling
@ComponentScan(basePackages = { "at.escapedoom.player", "at.escapedoom.spring.security",
        "at.escapedoom.spring.components", "at.escapedoom.spring.redis" })
public class PlayerApi {
    public static void main(String[] args) {
        SpringApplication.run(PlayerApi.class, args);
    }
}
