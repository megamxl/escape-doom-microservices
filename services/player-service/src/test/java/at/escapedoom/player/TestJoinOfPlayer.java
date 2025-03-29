package at.escapedoom.player;

import at.escapedoom.player.rest.model.EscapeRoomJoinResponse;
import at.escapedoom.player.service.LobbyService;
import at.escapedoom.spring.communication.data.api.TemplateApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest(classes = PlayerApi.class)
class TestJoinOfPlayer {

    @SuppressWarnings("resource")
    @Container
    static GenericContainer<?> redisContainer = new GenericContainer<>("redis:7.0.0")
            .withExposedPorts(6379);


    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("escapedoom.redis.host", redisContainer::getHost);
        registry.add("escapedoom.redis.port", () -> redisContainer.getMappedPort(6379));
    }

    @Autowired
    private LobbyService lobbyService;

    @MockBean
    private TemplateApi templateApi;

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
