package at.escapedoom.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@ComponentScan(basePackages = { "at.escapedoom.player", "at.escapedoom.spring.security",
        "at.escapedoom.spring.components" })
public class PlayerApi {
    public static void main(String[] args) {
        SpringApplication.run(PlayerApi.class, args);
    }
}
