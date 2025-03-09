package at.escapedoom.session.service;

import at.escapedoom.session.SessionApi;
import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.data.EscapeRoomSessionRepository;
import at.escapedoom.session.rest.model.EscapeRoomState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = SessionApi.class)
class EscapeRoomSessionServiceTest {

    @Autowired
    private EscapeRoomSessionService sessionService;

    @Autowired
    private EscapeRoomSessionRepository sessionRepository;

    @Test
    void testCreateSessionSuccessfully() {
        UUID templateId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Long roomPin = 123L;
        Long playTime = 60L;

        EscapeRoomSession session = sessionService.createSession(templateId, playTime, roomPin, userId);

        assertThat(session).isNotNull();
        assertThat(session.getEscapeRoomTemplateId()).isEqualTo(templateId);
        assertThat(session.getRoomPin()).isEqualTo(roomPin);
        assertThat(session.getUserId()).isEqualTo(userId);
        assertThat(session.getPlayTime()).isEqualTo(playTime);
        assertThat(session.getState()).isEqualTo(EscapeRoomState.OPEN);
    }

    @Test
    void testGetSessionByIdNotFound() {
        UUID nonExistentSessionId = UUID.randomUUID();
        assertThrows(RuntimeException.class, () -> sessionService.getSessionById(nonExistentSessionId));
    }

    @Test
    void testGetSessionByRoomPinNotFound() {
        assertThrows(RuntimeException.class, () -> sessionService.getSessionByRoomPin(99L));
    }

    @Test
    void testChangeSessionStatus() {
        UUID templateId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Long roomPin = 65L;
        Long playTime = 90L;

        EscapeRoomSession session = sessionService.createSession(templateId, playTime, roomPin, userId);

        EscapeRoomSession updatedSession = sessionService.changeSessionStatus(session.getEscapeRoomSessionId(),
                EscapeRoomState.STARTED);
        assertThat(updatedSession.getState()).isEqualTo(EscapeRoomState.STARTED);

        EscapeRoomSession closedSession = sessionService.changeSessionStatus(session.getEscapeRoomSessionId(),
                EscapeRoomState.CLOSED);
        assertThat(closedSession.getState()).isEqualTo(EscapeRoomState.CLOSED);
    }

    @Test
    void testGetSessionsByUserName() {
        UUID uuid = UUID.randomUUID();
        sessionService.createSession(UUID.randomUUID(), 60L, 12L, uuid);
        sessionService.createSession(UUID.randomUUID(), 75L, 23L, uuid);

        List<EscapeRoomSession> sessions = sessionService.getSessionsByUserUUID(uuid);
        assertThat(sessions).isNotEmpty();
        assertThat(sessions.size()).isEqualTo(2);
    }

    @Test
    void testGetSessionsByTags() {
        UUID uuid = UUID.randomUUID();
        EscapeRoomSession session = sessionService.createSession(UUID.randomUUID(), 60L, 34L, uuid);
        sessionService.addTagToSession(uuid, session.getEscapeRoomSessionId(), "VZ25");

        List<EscapeRoomSession> sessions = sessionService.getSessionsByTags(uuid, List.of("VZ25"));
        assertThat(sessions).isNotEmpty();
        assertThat(sessions.get(0).getTags()).contains("VZ25");
    }

    @Test
    void testAddAndRemoveTags() {
        UUID userId = UUID.randomUUID();
        EscapeRoomSession session = sessionService.createSession(UUID.randomUUID(), 60L, 98L, userId);

        sessionService.addTagToSession(userId, session.getEscapeRoomSessionId(), "SDE26");
        EscapeRoomSession updatedSession = sessionService.getSessionById(session.getEscapeRoomSessionId());
        assertThat(updatedSession.getTags()).contains("SDE26");

        sessionService.removeTagFromSession(userId, session.getEscapeRoomSessionId(), "SDE26");
        updatedSession = sessionService.getSessionById(session.getEscapeRoomSessionId());
        assertThat(updatedSession.getTags()).doesNotContain("SDE26");
    }
}
