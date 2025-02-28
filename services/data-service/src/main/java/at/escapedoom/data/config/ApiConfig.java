package at.escapedoom.data.config;

import at.escapedoom.data.delegate.TemplateApiDelegateImpl;
import at.escapedoom.data.services.controller.RiddleInvocationController;
import at.escapedoom.data.services.controller.SceneInvocationController;

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
    public SceneApiDelegate sceneApiDelegate(@Autowired SceneInvocationController delegate) {
        return delegate;
    }

    @Bean
    @Primary
    public RiddleApiDelegate riddleApiDelegate(@Autowired RiddleInvocationController delegate) {
        return delegate;
    }

}
