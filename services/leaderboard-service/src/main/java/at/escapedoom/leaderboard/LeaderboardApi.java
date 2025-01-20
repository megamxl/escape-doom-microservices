package at.escapedoom.leaderboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@ComponentScan(basePackages = { "at.escapedoom.leaderboard", "at.escapedoom.spring.security" })
public class LeaderboardApi {
    public static void main(String[] args) {
        SpringApplication.run(LeaderboardApi.class, args);
    }
}
