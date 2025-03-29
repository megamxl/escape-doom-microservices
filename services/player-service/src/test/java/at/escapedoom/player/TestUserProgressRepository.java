package at.escapedoom.player;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.spring.communication.data.api.TemplateApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EntityScan("at.escapedoom.player.data.postgres.entity")
@TestPropertySource(properties = { "escapedoom.redis.port=6379", "escapedoom.redis.host=localhost" })
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class TestUserProgressRepository {

    @Autowired
    UserProgressRepository userProgressRepository;

    @MockBean
    private TemplateApi templateApi;

    @Test
    void testTheAmountOfPeopleSolvedAStageIsCorrect() {
        // Add multiple user progress records
        userSaver("1", 2L);
        userSaver("2", 2L);
        userSaver("3", 2L);
        userSaver("1", 1L);
        userSaver("1", 1L);
        userSaver("1", 3L);
        userSaver("1", 2L);

        // Assert the number of users solving levels
        assertThat(userProgressRepository.getAmountOfUsersSolvedThisLevelByRoomPin(20L, 1L)).hasValue(7);
        assertThat(userProgressRepository.getAmountOfUsersSolvedThisLevelByRoomPin(20L, 2L)).hasValue(5);
        assertThat(userProgressRepository.getAmountOfUsersSolvedThisLevelByRoomPin(20L, 3L)).hasValue(1);
    }

    private void userSaver(String username, Long level) {
        userProgressRepository.save(UserProgress.builder().userName(username).currentEscapeRoomLevel(level).roomPin(20L)
                .lastRiddleSolvedAt(LocalDateTime.now()).build());
    }
}
