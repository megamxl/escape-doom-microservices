package at.escapedoom.data.config;

import at.escapedoom.data.rest.api.RiddleApiDelegate;
import at.escapedoom.data.rest.api.SceneApiDelegate;
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
    public SceneApiDelegate sceneApiDelegate(@Autowired SceneInvocationController delegate) {
        return delegate;
    }

    @Bean
    @Primary
    public RiddleApiDelegate riddleApiDelegate(@Autowired RiddleInvocationController delegate) {
        return delegate;
    }

}
