package at.escapedoom.leaderboard;

import at.escapedoom.leaderboard.rest.api.AllLeaderboardsApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class APIDelegates {

    @Bean
    @Primary
    public AllLeaderboardsApiDelegate allLeaderboardsApiDelegate (@Autowired AllLeaderboardsService delegate) {
      return  delegate;
    }

}