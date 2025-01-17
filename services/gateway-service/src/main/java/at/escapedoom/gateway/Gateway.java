package at.escapedoom.gateway;

import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@SpringBootApplication
@EnableWebFluxSecurity
@RestController
public class Gateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(Gateway.class);

    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
    }

    @GetMapping(value = "/test")
    public Mono<String> getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient)
            throws ParseException {
        SignedJWT signedJWT = (SignedJWT) JWTParser.parse(authorizedClient.getAccessToken().getTokenValue());

        return Mono.just(authorizedClient.getAccessToken().getTokenValue());
    }
}
