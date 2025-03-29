package at.escapedoom.player.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequiredArgsConstructor
public class WSController {

    private SimpMessagingTemplate template;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @Autowired
    public WSController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @RequestMapping(path = "/old/greetings/{lobbyId}", method = POST)
    public void greet(String greeting, String user, String lobbyId) {
        System.out.println("bin im greeting: " + greeting);
        this.template.convertAndSend("/topic/greetings/" + lobbyId, new TestMessage(greeting));
    }

    @RequestMapping(path = "/greetings/{lobbyId}", method = POST)
    public void update(PlayerNamesMessage message, String topic) {
        System.out.println("bin im update: " + message);
        this.template.convertAndSend(topic, message);
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public TestMessage respond(TestMessage message) throws Exception {
        Thread.sleep(1000);
        System.out.println("im in respond " + message.message);
        return new TestMessage("Hello, " + message.getMessage() + "!");
    }
}
