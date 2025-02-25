package at.escapedoom.session.data.repository;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.model.EscapeRoomState;
import at.escapedoom.spring.redis.data.models.SessionView;
import at.escapedoom.spring.redis.data.repositories.SessionViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EscapeRoomSessionService {

    private final EscapeRoomSessionRepository repository;
    private final SessionViewRepository sessionViewRepository;

    public EscapeRoomSession createSession(UUID templateId, Long playTime, Long roomPin, String userId) {
        EscapeRoomSession session = EscapeRoomSession.builder().escapeRoomSessionId(UUID.randomUUID())
                .escapeRoomTemplateId(templateId).userId(userId).roomPin(roomPin).playTime(playTime)
                .state(EscapeRoomState.OPEN).build();
      return saveAndChacheSession(session);
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
        // TODO hier könnte man noch eine art state machine reincoden die nur State Änderungen in eine Richtung erlaubt
        if (newState == EscapeRoomState.STARTED) {
            session.setStartTime(LocalDateTime.now());
            session.setEndTime(LocalDateTime.now().plusMinutes(session.getPlayTime()));
        } else if (newState == EscapeRoomState.CLOSED) {
            session.setEndTime(LocalDateTime.now());
        }
        return saveAndChacheSession(session);
    }

    public EscapeRoomSession getSessionById(UUID sessionId) {
        Optional<EscapeRoomSession> sessionOptional = repository.findById(sessionId);
        return sessionOptional.orElseThrow(() -> new RuntimeException("Session not found for ID: " + sessionId));
    }

    public List<EscapeRoomSession> getSessionsByUserName(String userName) {
        List<EscapeRoomSession> sessions = repository.getEscapeRoomSessionsByUserIdOrderByStartTimeDesc(userName);
        return sessions;
    }

    public List<EscapeRoomSession> getSessionsByTags(String userName, List<String> tags) {
        return repository.getEscapeRoomSessionsByUserIdAndTagsContains(userName, tags);
    }

    public EscapeRoomSession getSessionByRoomPin(Long roomPin) {
        Optional<EscapeRoomSession> sessionOptional = repository.getEscapeRoomSessionByRoomPin(roomPin);
        return sessionOptional.orElseThrow(() -> new RuntimeException("Session not found for roomPin: " + roomPin));
    }

    @NotNull
    private EscapeRoomSession saveAndChacheSession(EscapeRoomSession session) {
        EscapeRoomSession persistedSession = repository.save(session);
        sessionViewPublisher(persistedSession);
        return persistedSession;
    }

    public void sessionViewPublisher(EscapeRoomSession session) {

        SessionView sessionView = SessionView.builder()
                .roomPin(session.getRoomPin())
                .roomState(at.escapedoom.spring.redis.data.models.EscapeRoomState.valueOf(session.getState().name()))
                .escapeRoomTemplateId(session.getEscapeRoomTemplateId())
                .build();

        try {
            sessionViewRepository.save(sessionView);
        }catch (Exception e) {
            log.error("can't publish to redis -> no showstopper but check it: exception -> {}",e.getMessage());
        }
    }
}
