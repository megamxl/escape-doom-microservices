package at.escapedoom.player.config;

import at.escapedoom.player.rest.api.LevelApiController;
import at.escapedoom.player.rest.api.LevelApiDelegate;
import at.escapedoom.player.services.LevelDelegateService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RequiredArgsConstructor
@Configuration
public class ApiConfig {

    private final LevelDelegateService levelApiController;

    @Bean
    @Primary
    LevelApiDelegate returnDelegate(){
        return this.levelApiController;
    }

}
