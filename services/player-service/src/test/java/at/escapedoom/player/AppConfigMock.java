package at.escapedoom.player;

import at.escapedoom.player.data.postgres.repository.SolutionAttemptRepository;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.mock.MOCKEscapeRoomSessionRepositoryImpl;
import at.escapedoom.player.service.CodeCompilerApiInterfaceImpl;
import at.escapedoom.player.service.interfaces.CodeCompilerInterface;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class AppConfigMock {

    @Bean
    @Profile("test")
    public EscapeRoomSessionRepositoryService escapeRoomSessionRepositoryService(
            @Autowired MOCKEscapeRoomSessionRepositoryImpl escapeRoomSessionRepositoryService) {
        return escapeRoomSessionRepositoryService;
    }

    @Bean
    public at.escapedoom.spring.communication.session.api.SessionApi getSessionApi(@Autowired OkHttpClient client) {
        return new at.escapedoom.spring.communication.session.api.SessionApi(
                new at.escapedoom.spring.communication.session.invoker.ApiClient(client)
                        .setBasePath("http://localhost:8081/session-api/v1"));
    }

    @Bean
    public CodeCompilerInterface getCodeCompilerInterface(
            @Autowired SolutionAttemptRepository solutionAttemptRepository,
            @Autowired UserProgressRepository userProgressRepository) {
        return new CodeCompilerApiInterfaceImpl(solutionAttemptRepository, userProgressRepository);
    }

}
