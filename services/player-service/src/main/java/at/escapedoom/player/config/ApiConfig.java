package at.escapedoom.player.config;

import at.escapedoom.player.rest.api.LevelApiDelegate;
import at.escapedoom.player.rest.api.LobbyApiDelegate;
import at.escapedoom.player.service.delegates.GamePlayDelegateService;
import at.escapedoom.player.service.delegates.LobbyDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RequiredArgsConstructor
@Configuration
public class ApiConfig {

    private final GamePlayDelegateService levelApiController;

    private final LobbyDelegateService lobbyApiController;

    @Bean
    @Primary
    LevelApiDelegate returnDelegate() {
        return this.levelApiController;
    }

    @Bean
    @Primary
    LobbyApiDelegate returnLobbyDelegate() {
        return this.lobbyApiController;
    }

}
