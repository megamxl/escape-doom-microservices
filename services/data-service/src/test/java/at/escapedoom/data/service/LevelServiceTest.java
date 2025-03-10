package at.escapedoom.data.service;

import at.escapedoom.data.DataApi;
import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.TemplateRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.LevelCreationRequest;
import at.escapedoom.data.rest.model.LevelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = DataApi.class)
@ActiveProfiles("test")
class LevelServiceTest {

    private String VALID_LEVEL_ID = "";
    private static final String INVALID_LEVEL_ID = "05c48cb1-a3aa-4673-8d24-666666666666";

    @Autowired
    private LevelService service;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private SceneRepository sceneRepository;

    @BeforeEach
    void setup() {
        sceneRepository.deleteAllInBatch();
        levelRepository.deleteAllInBatch();
        templateRepository.deleteAllInBatch();
        levelRepository.flush();

        Level level = Level.builder().levelSequence(1).scenes(List.of()).build();
        VALID_LEVEL_ID = levelRepository.save(level).getLevelId().toString();
    }
    // region GET Tests

    @Test
    @Transactional
    void testGetAllLevels() {
        List<LevelDTO> levels = service.getAllLevels();
        assertThat(levels).hasSize(1);
        assertThat(levels.get(0).getLevelSequence()).isEqualTo(1);
    }

    @Test
    @Transactional
    void testGetLevelById() {
        LevelDTO level = service.getLevel(VALID_LEVEL_ID);
        assertThat(level.getLevelSequence()).isEqualTo(1);
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
    @Transactional
    void testUpdateLevel() {
        LevelDTO level = service.getLevel(VALID_LEVEL_ID);
        final int newSequence = 2;

        LevelDTO levelRequest = LevelDTO.builder().levelId(level.getLevelId()).levelSequence(newSequence)
                .scenes(level.getScenes()).riddle(level.getRiddle()).build();

        LevelDTO updatedLevel = service.updateLevel(level.getLevelId(), levelRequest);

        assertThat(updatedLevel.getLevelId()).isEqualTo(level.getLevelId());
        assertThat(updatedLevel.getLevelSequence()).isEqualTo(newSequence);
    }

    @Test
    void testUpdateLevelNotFoundError() {
        LevelDTO levelRequest = LevelDTO.builder().levelSequence(2).build();

        assertThatThrownBy(() -> service.updateLevel(INVALID_LEVEL_ID, levelRequest))
                .isInstanceOf(NoSuchElementException.class).hasMessageContaining("Level with ID");
    }
    // endregion

    @Test
    void testCreateLevel() {
        Template template = Template.builder().name("Test Template").description("A test template")
                .userId(UUID.randomUUID()).build();

        template = templateRepository.saveAndFlush(template);
        UUID templateId = template.getTemplateId();

        LevelCreationRequest request = LevelCreationRequest.builder().levelSequence(3).templateId(templateId.toString())
                .build();

        LevelDTO level = service.createLevel(request);

        assertThat(level).isNotNull();
        assertThat(level.getLevelSequence()).isEqualTo(3);
    }

    @Test
    void testCreateLevelNullError() {
        assertThatThrownBy(() -> service.createLevel(null)).isInstanceOf(AssertionError.class);
    }
    // endregion
}