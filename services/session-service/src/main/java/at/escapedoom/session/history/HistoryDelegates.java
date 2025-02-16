package at.escapedoom.session.history;

import at.escapedoom.session.rest.api.HistoryApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HistoryDelegates {
    @Bean
    @Primary
    public HistoryApiDelegate historyApiDelegate(@Autowired HistoryService delegate) {
        return delegate;
    }
}
