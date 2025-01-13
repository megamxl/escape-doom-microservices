package at.escapedoom.dataService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableMethodSecurity
@ComponentScan(basePackages = {"at.escapedoom.dataService", "at.escapedoom.spring.security" })
public class DataApi
{
    public static void main( String[] args )
    {
        SpringApplication.run(DataApi.class, args);
    }
}
