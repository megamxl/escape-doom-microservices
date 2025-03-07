package at.escapedoom.player.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public TestMessage respond(TestMessage message) throws Exception {
        Thread.sleep(1000);
        return new TestMessage("Hello, " + message.getMessage() + "!");
    }
}
