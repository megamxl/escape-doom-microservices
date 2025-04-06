package at.escapedoom.player.ws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WSController {

    private SimpMessagingTemplate template;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @Autowired
    public WSController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void update(PlayerNamesMessage message, String topic) {
        log.debug("Update player-names via WS as: " + message + " topic: " + topic);
        this.template.convertAndSend(topic, message);
    }
}
