package at.escapedoom.spring.components.okhttp;


import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OkHttpClientConfig {

    @Bean
    @Primary
    OkHttpClient getClient() {
        return new OkHttpClient
                .Builder()
                .addInterceptor(new AuthorizationHeaderIntercept())
                .build();
    }

}
