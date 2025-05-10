package at.escapedoom.session.service;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.data.EscapeRoomSessionRepository;
import at.escapedoom.session.rest.model.EscapeRoomState;
import at.escapedoom.spring.redis.RedisConfig;
import at.escapedoom.spring.redis.data.models.SessionView;
import at.escapedoom.spring.redis.data.repositories.SessionViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public EscapeRoomSession createSession(UUID templateId, Long playTime, Long roomPin, UUID userId) {
        EscapeRoomSession session = EscapeRoomSession.builder().sessionId(UUID.randomUUID()).templateId(templateId)
                .userId(userId).roomPin(roomPin).playTime(playTime).state(EscapeRoomState.OPEN).build();
        return saveAndChacheSession(session);
    }

    @Transactional
    public EscapeRoomSession addTagToSession(UUID userId, UUID sessionId, String tag) {
        EscapeRoomSession session = getSessionById(sessionId);
        if (!session.getUserId().equals(userId)) {
            throw new RuntimeException("Session user id does not match session user id");
        }
        if (!session.getTags().contains(tag)) {
            session.getTags().add(tag);
            return repository.save(session);
        } else {
            log.debug("Tag already exists in the session");
        }
        return session;
    }

    @Transactional
    public EscapeRoomSession removeTagFromSession(UUID userId, UUID sessionId, String tag) {
        EscapeRoomSession session = getSessionById(sessionId);
        if (!session.getUserId().equals(userId)) {
            throw new RuntimeException("Session user id does not match session user id");
        }
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

    public List<EscapeRoomSession> getSessionsByUserUUID(UUID userId) {
        List<EscapeRoomSession> sessions = repository.getEscapeRoomSessionsByUserIdOrderByStartTimeDesc(userId);
        return sessions;
    }

    @Transactional
    public List<EscapeRoomSession> getSessionsByTags(UUID userId, List<String> tags) {
        return repository.getEscapeRoomSessionsByUserIdAndTagsContains(userId, tags);
    }

    public EscapeRoomSession getSessionByRoomPin(Long roomPin) {
        Optional<EscapeRoomSession> sessionOptional = repository.getEscapeRoomSessionByRoomPin(roomPin);
        return sessionOptional.orElseThrow(() -> new RuntimeException("Session not found for roomPin: " + roomPin));
    }

    @NotNull
    public EscapeRoomSession saveAndChacheSession(EscapeRoomSession session) {
        EscapeRoomSession persistedSession = repository.save(session);
        sessionViewPublisher(persistedSession);
        return persistedSession;
    }

    public void sessionViewPublisher(EscapeRoomSession session) {

        SessionView sessionView = SessionView.builder().roomPin(session.getRoomPin())
                .roomState(at.escapedoom.spring.redis.data.models.EscapeRoomState.valueOf(session.getState().name()))
                .escapeRoomTemplateId(session.getTemplateId()).build();

        try {
            sessionViewRepository.save(sessionView);
            redisTemplate.convertAndSend(RedisConfig.sessionStateChangeTopic(),
                    objectMapper.writeValueAsString(sessionView));
        } catch (Exception e) {
            log.error("can't publish to redis -> no showstopper but check it: exception -> {}", e.getMessage());
        }
    }
}
