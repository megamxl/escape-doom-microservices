package at.escapedoom.session.service;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.data.repository.EscapeRoomSessionRepository;
import at.escapedoom.session.rest.model.EscapeRoomState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class EscapeRoomSessionService {
    private final EscapeRoomSessionRepository repository;

    public EscapeRoomSessionService(EscapeRoomSessionRepository repository) {
        this.repository = repository;
    }

    public EscapeRoomSession createSession(UUID templateId, Long playTime, Long roomPin,String userId) {
        EscapeRoomSession session = new EscapeRoomSession();

        session.setEscapeRoomSessionId(UUID.randomUUID());
        session.setEscapeRoomTemplateId(templateId);
        session.setUserId(userId);
        session.setRoomPin(roomPin);
        session.setPlayTime(playTime);
        session.setState(EscapeRoomState.OPEN);

        return repository.save(session);
    }

    public EscapeRoomSession getSessionById(UUID sessionId) {
        Optional<EscapeRoomSession> sessionOptional = repository.findById(sessionId);
        return sessionOptional.orElseThrow(() -> new RuntimeException("Session not found for ID: " + sessionId));
    }

    public List<EscapeRoomSession> getSessionsByUserName(String userName) {
        List<EscapeRoomSession> sessions = repository.getEscapeRoomSessionsByUserIdOrderByStartTimeDesc(userName);
        return sessions;
    }

    public EscapeRoomSession addTagToSession(UUID sessionId, String tag) {
        EscapeRoomSession session = getSessionById(sessionId);
        if (!session.getTags().contains(tag)) {
            session.getTags().add(tag);
            return repository.save(session);
        } else {
            log.debug("Tag already exists in the session");
        }
        return session;
    }


    public EscapeRoomSession removeTagFromSession(UUID sessionId, String tag) {
        EscapeRoomSession session = getSessionById(sessionId);
        if (session.getTags().contains(tag)) {
            session.getTags().remove(tag);
            return repository.save(session);
        } else {
            log.debug("Tag does not exist in the session");
        }
        return session;
    }

    public EscapeRoomSession changeSessionStatus(UUID sessionId, EscapeRoomState newState) {
        EscapeRoomSession session = getSessionById(sessionId);
        session.setState(newState);
        //TODO hier könnte man noch eine art state machine reincoden die nur State Änderungen in eine Richtung erlaubt
        if(newState == EscapeRoomState.STARTED) {
            session.setStartTime(LocalDateTime.now());
            session.setEndTime(LocalDateTime.now().plusMinutes(session.getPlayTime()));
        } else if(newState == EscapeRoomState.CLOSED) {
            session.setEndTime(LocalDateTime.now());
        }
        return repository.save(session);
    }
}
