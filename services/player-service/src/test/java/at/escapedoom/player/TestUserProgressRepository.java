package at.escapedoom.player;

import at.escapedoom.player.data.postgres.entity.UserProgress;
import at.escapedoom.player.data.postgres.repository.UserProgressRepository;
import at.escapedoom.spring.communication.data.api.TemplateApi;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Optional;

@Testcontainers
@SpringBootTest
@EntityScan("at.escapedoom.player.data.postgres.entity")
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
class TestUserProgressRepository {

    @SuppressWarnings("resource")
    @Container
    static GenericContainer<?> redisContainer = new GenericContainer<>("redis:7.0.0").withExposedPorts(6379);

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("escapedoom.redis.host", redisContainer::getHost);
        registry.add("escapedoom.redis.port", () -> redisContainer.getMappedPort(6379));
    }

    @Autowired
    UserProgressRepository userProgressRepository;

    @MockBean
    private TemplateApi templateApi;

    @Test
    void testTheAmountOfPeopleSolvedAStageIsCorrect() {

        // Arrange
        userSaver("1", 2L);
        userSaver("2", 2L);
        userSaver("3", 2L);
        userSaver("4", 1L);
        userSaver("5", 1L);
        userSaver("6", 3L);
        userSaver("7", 2L);

        // Act
        Optional<Integer> amountOfUsersSolvedThisLevel1 = userProgressRepository
                .getAmountOfUsersSolvedThisLevelByRoomPin(20L, 1L);
        Optional<Integer> amountOfUsersSolvedThisLevel2 = userProgressRepository
                .getAmountOfUsersSolvedThisLevelByRoomPin(20L, 2L);
        Optional<Integer> amountOfUsersSolvedThisLevel3 = userProgressRepository
                .getAmountOfUsersSolvedThisLevelByRoomPin(20L, 3L);

        AssertionsForClassTypes.assertThat(amountOfUsersSolvedThisLevel1.isPresent()).isTrue();
        AssertionsForClassTypes.assertThat(amountOfUsersSolvedThisLevel1.get()).isEqualTo(7);

        AssertionsForClassTypes.assertThat(amountOfUsersSolvedThisLevel2.isPresent()).isTrue();
        AssertionsForClassTypes.assertThat(amountOfUsersSolvedThisLevel2.get()).isEqualTo(5);

        AssertionsForClassTypes.assertThat(amountOfUsersSolvedThisLevel3.isPresent()).isTrue();
        AssertionsForClassTypes.assertThat(amountOfUsersSolvedThisLevel3.get()).isEqualTo(1);

    }

    private void userSaver(String username, Long currentEscapeRoomLevel) {

        userProgressRepository
                .save(UserProgress.builder().userName(username).currentEscapeRoomLevel(currentEscapeRoomLevel)
                        .roomPin(20L).lastRiddleSolvedAt(LocalDateTime.now()).build());

    }
}
