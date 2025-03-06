package at.escapedoom.data.config;

import at.escapedoom.data.rest.api.LevelApiDelegate;
import at.escapedoom.data.rest.api.RiddleApiDelegate;
import at.escapedoom.data.rest.api.SceneApiDelegate;
import at.escapedoom.data.rest.api.TemplateApiDelegate;
import at.escapedoom.data.service.delegate.LevelApiDelegateImpl;
import at.escapedoom.data.service.delegate.RiddleApiDelegateImpl;
import at.escapedoom.data.service.delegate.SceneApiDelegateImpl;
import at.escapedoom.data.service.delegate.TemplateApiDelegateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApiConfig {

    @Bean
    @Primary
    public TemplateApiDelegate templateApiDelegate(@Autowired TemplateApiDelegateImpl delegate) {
        return delegate;
    }

    @Bean
    @Primary
    public SceneApiDelegate sceneApiDelegate(@Autowired SceneApiDelegateImpl delegate) {
        return delegate;
    }

    @Bean
    @Primary
    public RiddleApiDelegate riddleApiDelegate(@Autowired RiddleApiDelegateImpl delegate) {
        return delegate;
    }

    @Bean
    @Primary
    public LevelApiDelegate levelApiDelegate(@Autowired LevelApiDelegateImpl delegate) {
        return delegate;
    }
}
