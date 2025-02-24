package at.escapedoom.data.config;

import at.escapedoom.data.rest.api.TemplateApiDelegate;
import at.escapedoom.data.services.TemplateApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApiConfig {

    @Bean
    @Primary
    public TemplateApiDelegate templateApiDelegate(@Autowired TemplateApiService delegate) {
        return delegate;
    }

}
