package at.escapedoom.player;

import at.escapedoom.player.entity.UserProgress;
import at.escapedoom.player.repository.UserProgressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = PlayerApi.class)
public class TestUserProgressRepository {

    @Autowired
    UserProgressRepository userProgressRepository;



    @Test
    void testTheAmountOfPeopleSolvedAStageIsCorrect(){

        //Arrange
        userSaver("1", 2L);
        userSaver("2", 2L);
        userSaver("3", 2L);
        userSaver("1", 1L);
        userSaver("1", 1L);
        userSaver("1", 3L);
        userSaver("1", 2L);

        //Act
        Optional<Integer> amountOfUsersSolvedThisLevel1 = userProgressRepository.getAmountOfUsersSolvedThisLevelByRoomPin(20L, 1L);
        Optional<Integer> amountOfUsersSolvedThisLevel2 = userProgressRepository.getAmountOfUsersSolvedThisLevelByRoomPin(20L, 2L);
        Optional<Integer> amountOfUsersSolvedThisLevel3 = userProgressRepository.getAmountOfUsersSolvedThisLevelByRoomPin(20L, 3L);

        assertThat(amountOfUsersSolvedThisLevel1.isPresent()).isTrue();
        assertThat(amountOfUsersSolvedThisLevel1.get()).isEqualTo(7);

        assertThat(amountOfUsersSolvedThisLevel2.isPresent()).isTrue();
        assertThat(amountOfUsersSolvedThisLevel2.get()).isEqualTo(5);

        assertThat(amountOfUsersSolvedThisLevel3.isPresent()).isTrue();
        assertThat(amountOfUsersSolvedThisLevel3.get()).isEqualTo(1);

    }


    private void userSaver(String username, Long currentEscapeRoomLevel) {

        userProgressRepository.save(
                UserProgress.builder()
                        .userName(username)
                        .currentEscapeRoomLevel(currentEscapeRoomLevel)
                        .roomPin(20L)
                        .lastRiddleSolvedAt(LocalDateTime.now())
                        .build()
        );

    }


}
