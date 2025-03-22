package at.escapedoom.player.service;

import at.escapedoom.player.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
@RequiredArgsConstructor
public class RedisMessagingService {

    private final RedisTemplate<String, String> redisTemplate;

    public void sendMessage(String message) {
        redisTemplate.convertAndSend(AppConfig.nameChangeChannel(), message);
    }
}
