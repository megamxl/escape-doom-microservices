package at.escapedoom.player.config;

import at.escapedoom.player.service.SessionCommunicationService;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import at.escapedoom.spring.communication.session.api.SessionApi;
import at.escapedoom.spring.communication.session.invoker.ApiClient;
import okhttp3.OkHttpClient;
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

    @Bean
    public SessionApi getSessionApi(@Autowired OkHttpClient client) {
        return new SessionApi(new ApiClient(client).setBasePath("http://localhost:8081/session-api/v1"));
    }

}
