package at.escapedoom.player;

import at.escapedoom.player.rest.model.EscapeRoomJoinResponse;
import at.escapedoom.player.service.LobbyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = PlayerApi.class)
public class TestJoinOfPlayer {

    @Autowired
    private LobbyService lobbyService;

    @Test
    void testJoinOfSessionNotPossibleBecauseOfState() {
        assertThrows(IllegalArgumentException.class, () -> lobbyService.joinSessionByRoomPin(100000L, "maxl"));
    }

    @Test
    void testJoinOfSessionNotInSessionDataStore() {
        assertThrows(NoSuchElementException.class, () -> lobbyService.joinSessionByRoomPin(600000L, "maxl"));
    }

    @Test
    void testJoinOfSessionPossible() {
        EscapeRoomJoinResponse escapeRoomJoinResponse = lobbyService.joinSessionByRoomPin(300000L, "maxl");

        assertThat(escapeRoomJoinResponse).isNotNull();

        assertThat(escapeRoomJoinResponse.getPlayerName()).isEqualTo("maxl");

        assertThat(escapeRoomJoinResponse.getPlayerSessionId()).isInstanceOf(UUID.class);
    }
}
