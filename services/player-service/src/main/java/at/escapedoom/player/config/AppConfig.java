package at.escapedoom.player.config;

import at.escapedoom.player.service.RedisNameUpdateReceiver;
import at.escapedoom.player.service.RedisSessionStateUpdateReceiver;
import at.escapedoom.player.data.postgres.repository.SolutionAttemptRepository;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.player.service.CodeCompilerApiInterfaceImpl;
import at.escapedoom.player.service.interfaces.CodeCompilerInterface;
import at.escapedoom.spring.communication.data.api.TemplateApi;
import at.escapedoom.spring.communication.session.api.SessionApi;
import at.escapedoom.player.service.SessionCommunicationService;
import at.escapedoom.player.service.interfaces.EscapeRoomSessionRepositoryService;
import at.escapedoom.spring.communication.session.invoker.ApiClient;

import at.escapedoom.spring.redis.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@Profile("!test")
@Slf4j
public class AppConfig {

    @Value("${escapedoom.communication.session-api-url}")
    private String sessionApiUrl;

    @Value("${escapedoom.communication.data-api-url}")
    private String dataApiUrl;

    @Primary
    @Bean
    public EscapeRoomSessionRepositoryService escapeRoomSessionRepositoryService(
            @Autowired SessionCommunicationService escapeRoomSessionRepositoryService) {
        return escapeRoomSessionRepositoryService;
    }

    @Bean
    public SessionApi getSessionApi(@Autowired OkHttpClient client) {
        log.info("Using SessionApi Url: {}", sessionApiUrl);
        return new SessionApi(new ApiClient(client).setBasePath(sessionApiUrl));
    }

    @Bean
    public TemplateApi getTemplateApi(@Autowired OkHttpClient client) {
        log.info("Using TemplateApi Url: {}", dataApiUrl);
        return new TemplateApi(
                new at.escapedoom.spring.communication.data.invoker.ApiClient(client).setBasePath(dataApiUrl));
    }

    @Bean
    @Qualifier("nameChange")
    public MessageListenerAdapter listenerAdapter(RedisNameUpdateReceiver redisNameUpdateReceiver) {
        return new MessageListenerAdapter(redisNameUpdateReceiver, "receiveMessage");
    }

    @Bean
    @Qualifier("stateChange")
    public MessageListenerAdapter listenerAdapterSessionUpdateChange(RedisSessionStateUpdateReceiver stateChange) {
        return new MessageListenerAdapter(stateChange, "receiveMessage");
    }

    public static String nameChangeChannel() {
        return "nameChangeChannel";
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
            @Qualifier("nameChange") MessageListenerAdapter nameChange,
            @Qualifier("stateChange") MessageListenerAdapter stateChange) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(nameChange, new PatternTopic(nameChangeChannel()));
        container.addMessageListener(stateChange, new PatternTopic(RedisConfig.sessionStateChangeTopic()));

        return container;
    }

    @Bean
    public CodeCompilerInterface getCodeCompilerInterface(
            @Autowired SolutionAttemptRepository solutionAttemptRepository,
            @Autowired UserProgressRepository userProgressRepository) {
        return new CodeCompilerApiInterfaceImpl(solutionAttemptRepository, userProgressRepository);
    }

}
