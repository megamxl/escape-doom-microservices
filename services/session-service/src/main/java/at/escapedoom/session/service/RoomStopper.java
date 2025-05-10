package at.escapedoom.session.service;

import at.escapedoom.session.data.EscapeRoomSessionRepository;
import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.model.EscapeRoomState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoomStopper {

    private final EscapeRoomSessionRepository escapeRoomSessionRepository;
    private final EscapeRoomSessionService escapeRoomSessionService;

    @Scheduled(fixedRate = 60000)
    public void HouseKeeper() {

        List<EscapeRoomSession> allByStatePlaying = escapeRoomSessionRepository.getEscapeRoomSessionsByStateOpenAndEndTimeAfter(LocalDateTime.now());
        if (allByStatePlaying.isEmpty()) {
            log.debug("No room to Stop");
            return;
        }
        for (EscapeRoomSession openLobby : allByStatePlaying) {
            openLobby.setState(EscapeRoomState.FINISHED);
            escapeRoomSessionService.saveAndChacheSession(openLobby);
            log.info("Stopped Room {}", openLobby.getRoomPin());
        }
    }
}


