package at.escapedoom.leaderboard.config;

import at.escapedoom.spring.communication.session.api.SessionApi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class AppConfig {

    @Value("${escapedoom.communication.session-api-url}")
    private String sessionApiUrl;

    @Bean
    public SessionApi getSessionApi(@Autowired OkHttpClient client) {
        return new SessionApi(new at.escapedoom.spring.communication.session.invoker.ApiClient(client)
                .setBasePath(sessionApiUrl));
    }
}
