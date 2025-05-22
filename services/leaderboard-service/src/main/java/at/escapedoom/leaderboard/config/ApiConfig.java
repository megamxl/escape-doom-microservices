package at.escapedoom.leaderboard.config;

import at.escapedoom.leaderboard.rest.api.SessionLeaderboardApiDelegate;
import at.escapedoom.leaderboard.service.delegate.AllLeaderboardsApiDelegateImpl;
import at.escapedoom.leaderboard.rest.api.AllLeaderboardsApiDelegate;
import at.escapedoom.leaderboard.service.delegate.SessionLeaderboardApiDelegateImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RequiredArgsConstructor
@Configuration
public class ApiConfig {

    private final AllLeaderboardsApiDelegateImpl allLeaderboardsApiDelegate;
    private final SessionLeaderboardApiDelegateImpl sessionLeaderboardApiDelegate;

    @Bean
    @Primary
    public AllLeaderboardsApiDelegate allLeaderboardsApiDelegate() {
        return this.allLeaderboardsApiDelegate;
    }

    @Bean
    @Primary
    public SessionLeaderboardApiDelegate sessionLeaderboardApiDelegate() {
        return this.sessionLeaderboardApiDelegate;
    }

}