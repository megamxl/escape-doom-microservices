package at.escapedoom.player.config;

import at.escapedoom.player.service.SessionCommunicationService;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class AppConfig {

    @Primary
    @Bean
    public EscapeRoomSessionRepositoryService escapeRoomSessionRepositoryService(
            @Autowired SessionCommunicationService escapeRoomSessionRepositoryService) {
        return escapeRoomSessionRepositoryService;
    }

}
