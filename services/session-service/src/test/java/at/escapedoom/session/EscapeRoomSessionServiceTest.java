package at.escapedoom.session;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.data.repository.EscapeRoomSessionRepository;
import at.escapedoom.session.data.repository.EscapeRoomSessionService;
import at.escapedoom.session.rest.model.EscapeRoomState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = SessionApi.class)
class EscapeRoomSessionServiceTest {

    @Autowired
    @Lazy
    private EscapeRoomSessionService sessionService;

    @Autowired
    private EscapeRoomSessionRepository sessionRepository;

    @Test
    void testCreateSessionSuccessfully() {
        UUID templateId = UUID.randomUUID();
        String userName = "testUser";
        Long roomPin = 123L;
        Long playTime = 60L;

        EscapeRoomSession session = sessionService.createSession(templateId, playTime, roomPin, userName);

        assertThat(session).isNotNull();
        assertThat(session.getEscapeRoomTemplateId()).isEqualTo(templateId);
        assertThat(session.getRoomPin()).isEqualTo(roomPin);
        assertThat(session.getUserId()).isEqualTo(userName);
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
        String userName = "player1";
        Long roomPin = 65L;
        Long playTime = 90L;

        EscapeRoomSession session = sessionService.createSession(templateId, playTime, roomPin, userName);

        EscapeRoomSession updatedSession = sessionService.changeSessionStatus(session.getEscapeRoomSessionId(), EscapeRoomState.STARTED);
        assertThat(updatedSession.getState()).isEqualTo(EscapeRoomState.STARTED);

        EscapeRoomSession closedSession = sessionService.changeSessionStatus(session.getEscapeRoomSessionId(), EscapeRoomState.CLOSED);
        assertThat(closedSession.getState()).isEqualTo(EscapeRoomState.CLOSED);
    }

    @Test
    void testGetSessionsByUserName() {
        String userName = "testUser2";
        sessionService.createSession(UUID.randomUUID(), 60L, 12L, userName);
        sessionService.createSession(UUID.randomUUID(), 75L, 23L, userName);

        List<EscapeRoomSession> sessions = sessionService.getSessionsByUserName(userName);
        assertThat(sessions).isNotEmpty();
        assertThat(sessions.size()).isEqualTo(2);
    }

    @Test
    void testGetSessionsByTags() {
        String userName = "testUser3";
        EscapeRoomSession session = sessionService.createSession(UUID.randomUUID(), 60L, 34L, userName);
        sessionService.addTagToSession(session.getEscapeRoomSessionId(), "VZ25");

        List<EscapeRoomSession> sessions = sessionService.getSessionsByTags(userName, List.of("VZ25"));
        assertThat(sessions).isNotEmpty();
        assertThat(sessions.get(0).getTags()).contains("VZ25");
    }

    @Test
    void testAddAndRemoveTags() {
        String userName = "testUser4";
        EscapeRoomSession session = sessionService.createSession(UUID.randomUUID(), 60L, 98L, userName);

        sessionService.addTagToSession(session.getEscapeRoomSessionId(), "SDE26");
        EscapeRoomSession updatedSession = sessionService.getSessionById(session.getEscapeRoomSessionId());
        assertThat(updatedSession.getTags()).contains("SDE26");

        sessionService.removeTagFromSession(session.getEscapeRoomSessionId(), "SDE26");
        updatedSession = sessionService.getSessionById(session.getEscapeRoomSessionId());
        assertThat(updatedSession.getTags()).doesNotContain("SDE26");
    }
}
