package at.escapedoom.player.config;

import at.escapedoom.player.services.MOCKEscapeRoomSessionRepositoryImpl;
import at.escapedoom.player.services.interfaces.EscapeRoomSessionRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Primary
    @Bean
    public EscapeRoomSessionRepositoryService escapeRoomSessionRepositoryService(
            @Autowired MOCKEscapeRoomSessionRepositoryImpl escapeRoomSessionRepositoryService) {
        return escapeRoomSessionRepositoryService;
    }

}
