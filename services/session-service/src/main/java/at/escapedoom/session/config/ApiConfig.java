package at.escapedoom.session.config;

import at.escapedoom.session.rest.api.HistoryApiDelegate;
import at.escapedoom.session.rest.api.ManagementApiDelegate;
import at.escapedoom.session.rest.api.SessionApiDelegate;
import at.escapedoom.session.rest.api.TagsApiDelegate;
import at.escapedoom.session.service.delegate.HistoryApiDelegateImpl;
import at.escapedoom.session.service.delegate.ManagementApiDelegateImpl;
import at.escapedoom.session.service.delegate.SessionApiDelegateImpl;
import at.escapedoom.session.service.delegate.TagsApiDelegateImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RequiredArgsConstructor
@Configuration
public class ApiConfig {

    private final HistoryApiDelegateImpl historyApiDelegate;
    private final ManagementApiDelegateImpl managementApiDelegate;
    private final TagsApiDelegateImpl tagsApiDelegate;
    private final SessionApiDelegateImpl sessionApiDelegate;

    @Bean
    @Primary
    public HistoryApiDelegate historyApiDelegate() {
        return this.historyApiDelegate;
    }

    @Bean
    @Primary
    public ManagementApiDelegate managementApiDelegate() {
        return this.managementApiDelegate;
    }

    @Bean
    @Primary
    public TagsApiDelegate tagsApiDelegate() {
        return this.tagsApiDelegate;
    }

    @Bean
    @Primary
    public SessionApiDelegate sessionApiDelegate() {
        return this.sessionApiDelegate;
    }
}
