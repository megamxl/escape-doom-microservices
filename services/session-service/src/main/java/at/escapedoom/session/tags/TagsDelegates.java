package at.escapedoom.session.tags;

import at.escapedoom.session.rest.api.TagsApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TagsDelegates {
    @Bean
    @Primary
    public TagsApiDelegate tagsApiDelegate(@Autowired TagsService delegate) {
        return delegate;
    }
}
