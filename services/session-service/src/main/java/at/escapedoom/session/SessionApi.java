package at.escapedoom.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@EnableScheduling
@ComponentScan(basePackages = { "at.escapedoom.session", "at.escapedoom.spring.security", "at.escapedoom.spring.redis",
        "at.escapedoom.spring.components" })
public class SessionApi {
    public static void main(String[] args) {
        SpringApplication.run(SessionApi.class, args);
    }
}
