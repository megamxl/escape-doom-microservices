package at.escapedoom.player.service;

import org.springframework.stereotype.Component;

@Component
public class RedisReceiver {

    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
    }
}
