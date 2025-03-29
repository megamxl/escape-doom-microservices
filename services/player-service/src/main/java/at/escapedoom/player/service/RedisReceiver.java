package at.escapedoom.player.service;

import at.escapedoom.player.data.redis.PlayerJoinedEvent;
import at.escapedoom.player.ws.WSController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class RedisReceiver {

    final WSController controller;

    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
        String jsonString = extractJson(message);

        if (jsonString != null) {
            PlayerJoinedEvent playerJoinedEvent = new Gson().fromJson(jsonString, PlayerJoinedEvent.class);
            controller.greet(playerJoinedEvent.getUserName(), playerJoinedEvent.getUserIdentifier(),
                    playerJoinedEvent.getRoomPin());
        }
        // controller.greet();
    }

    private static String extractJson(String text) {
        Pattern pattern = Pattern.compile("\\{.*\\}");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}
