package at.escapedoom.data.service;

import at.escapedoom.data.DataApi;
import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.rest.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = DataApi.class)
@ActiveProfiles("test")
class LevelServiceTest {

    private String VALID_LEVEL_ID = "";
    private static final String INVALID_LEVEL_ID = "05c48cb1-a3aa-4673-8d24-666666666666";

    @Autowired
    private LevelService service;

    @Autowired
    private LevelRepository repository;

    @Transactional
    @BeforeEach
    void setup() {
        repository.deleteAllInBatch();
        repository.flush();

        Level level = Level.builder().levelSequence(1).scenes(List.of()).build();
        VALID_LEVEL_ID = repository.save(level).getLevelId().toString();
    }

    // region GET Tests
    @Test
    void testGetAllLevels() {
        List<EscapeRoomLevelDTO> levels = service.getAllLevels();
        assertThat(levels).hasSize(1);
        assertThat(levels.get(0).getSequence()).isEqualTo(1);
    }

    @Test
    void testGetLevelById() {
        EscapeRoomLevelDTO level = service.getLevel(VALID_LEVEL_ID);
        assertThat(level.getSequence()).isEqualTo(1);
    }

    @Test
    void testGetLevelByIdError() {
        assertThatThrownBy(() -> service.getLevel(INVALID_LEVEL_ID)).isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Level with ID");
    }
    // endregion

    // region DELETE Tests
    @Test
    void testDeleteLevel() {
        DeleteLevelSuccessDTO response = service.deleteLevel(VALID_LEVEL_ID);
        assertThat(response.getMessage()).isEqualTo("Level deleted successfully");
    }

    @Test
    void testDeleteLevelNotFoundError() {
        assertThatThrownBy(() -> service.deleteLevel(INVALID_LEVEL_ID)).isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Level with ID");
    }
    // endregion

    // region PUT Tests
    @Test
    void testUpdateLevel() {
        EscapeRoomLevelDTO level = service.getLevel(VALID_LEVEL_ID);

        final int newSequence = 2;

        EscapeRoomLevelDTO levelRequest = EscapeRoomLevelDTO.builder().levelId(level.getLevelId()).sequence(newSequence)
                .scenes(level.getScenes()).riddle(level.getRiddle()).build();

        EscapeRoomLevelDTO updatedLevel = service.updateLevel(level.getLevelId(), levelRequest);

        assertThat(updatedLevel.getLevelId()).isEqualTo(level.getLevelId());
        assertThat(updatedLevel.getSequence()).isEqualTo(newSequence);
    }

    @Test
    void testUpdateLevelNotFoundError() {
        EscapeRoomLevelDTO levelRequest = EscapeRoomLevelDTO.builder().sequence(2).build();

        assertThatThrownBy(() -> service.updateLevel(INVALID_LEVEL_ID, levelRequest))
                .isInstanceOf(NoSuchElementException.class).hasMessageContaining("Level with ID");
    }
    // endregion

    // region POST Tests
    //TODO: @Mark - Comment back in when fixed to new structure
//    @Test
//    void testCreateLevel() {
//        EscapeRoomLevelDTO levelRequest = EscapeRoomLevelDTO.builder().sequence(3).scenes(List.of()).build();
//
//        EscapeRoomLevelDTO level = service.createLevel(levelRequest);
//
//        assertThat(level.getSequence()).isEqualTo(3);
//    }

    @Test
    void testCreateLevelNullError() {
        assertThatThrownBy(() -> service.createLevel(null)).isInstanceOf(AssertionError.class);
    }

    // endregion
}
