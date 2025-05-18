package at.escapedoom.data.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class SecurityTestConfig {

    @Bean
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/v1/**").authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
