package at.escapedoom.session.management;

import at.escapedoom.session.rest.api.ManagementApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ManagementDelegates {
    @Bean
    @Primary
    public ManagementApiDelegate managementApiDelegate(@Autowired ManagementService delegate) {
        return delegate;
    }
}
