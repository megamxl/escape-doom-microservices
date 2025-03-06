package at.escapedoom.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@EntityScan(basePackages = "at.escapedoom.data.data.entity")
@ComponentScan(basePackages = { "at.escapedoom.data", "at.escapedoom.spring.security" })
public class DataApi {
    public static void main(String[] args) {
        SpringApplication.run(DataApi.class, args);
    }
}
