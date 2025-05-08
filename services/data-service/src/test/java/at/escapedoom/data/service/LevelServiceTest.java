package at.escapedoom.data.service;

import at.escapedoom.data.DataApi;
import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.RiddleRepository;
import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.TemplateRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.rest.model.CodingLanguage;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.LevelCreationRequest;
import at.escapedoom.data.rest.model.LevelDTO;
import org.junit.jupiter.api.AfterEach;
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

    @Autowired
    private RiddleRepository riddleRepository;

    @AfterEach
    @Transactional
    void tearDown() {
        templateRepository.deleteAll();
    }

    @BeforeEach
    @Transactional
    void setup() {
        riddleRepository.deleteAllInBatch();
        levelRepository.deleteAllInBatch();
        sceneRepository.deleteAllInBatch();
        templateRepository.deleteAllInBatch();

        riddleRepository.flush();
        levelRepository.flush();
        sceneRepository.flush();
        templateRepository.flush();

        Template template = Template.builder().name("Test Template").description("Test template description")
                .userId(UUID.randomUUID()).build();
        template = templateRepository.saveAndFlush(template);

        Level level = Level.builder().name("Test Level").levelSequence(1).scenes(List.of())
                .templateId(template.getTemplateId()).build();
        level = levelRepository.saveAndFlush(level);

        Riddle riddle = Riddle.builder().input("1,2").expectedOutput("3")
                .functionSignature("public static int add(int a, int b)").variableName("sum")
                .language(CodingLanguage.JAVA).level(level).build();

        riddle = riddleRepository.saveAndFlush(riddle);

        level.setRiddle(riddle);
        levelRepository.saveAndFlush(level);

        VALID_LEVEL_ID = level.getLevelId().toString();
    }

    @Test
    @Transactional
    void testGetAllLevels() {
        List<LevelDTO> levels = service.getAllLevels();

        System.out.println("Levels found: " + levels.size());
        for (LevelDTO level : levels) {
            System.out.println(level);
        }

        assertThat(levels).hasSize(1);
        assertThat(levels.get(0).getLevelSequence()).isEqualTo(1);
    }

    @Test
    @Transactional
    void testGetLevelByIdById() {
        LevelDTO level = service.getLevelById(VALID_LEVEL_ID);
        assertThat(level.getLevelSequence()).isEqualTo(1);
    }

    @Test
    @Transactional
    void testGetLevelByIdByIdError() {
        assertThatThrownBy(() -> service.getLevelById(INVALID_LEVEL_ID)).isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Level with ID");
    }
    // endregion

    // region DELETE Tests
    @Test
    @Transactional
    void testDeleteLevel() {
        DeleteLevelSuccessDTO response = service.deleteLevel(VALID_LEVEL_ID);
        assertThat(response.getMessage()).isEqualTo("Level deleted successfully");
    }

    @Test
    @Transactional
    void testDeleteLevelNotFoundError() {
        assertThatThrownBy(() -> service.deleteLevel(INVALID_LEVEL_ID)).isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Level with ID");
    }
    // endregion

    // region PUT Tests
    @Test
    @Transactional
    void testUpdateLevel() {
        LevelDTO level = service.getLevelById(VALID_LEVEL_ID);
        final int newSequence = 2;

        LevelDTO levelRequest = LevelDTO.builder().levelId(level.getLevelId()).levelSequence(newSequence)
                .scenes(level.getScenes()).riddle(level.getRiddle()).name("Classroom").build();

        LevelDTO updatedLevel = service.updateLevel(level.getLevelId(), levelRequest);

        assertThat(updatedLevel.getLevelId()).isEqualTo(level.getLevelId());
        assertThat(updatedLevel.getLevelSequence()).isEqualTo(newSequence);
    }

    @Test
    @Transactional
    void testUpdateLevelNotFoundError() {
        LevelDTO levelRequest = LevelDTO.builder().levelSequence(2).build();

        assertThatThrownBy(() -> service.updateLevel(INVALID_LEVEL_ID, levelRequest))
                .isInstanceOf(NoSuchElementException.class).hasMessageContaining("Level with ID");
    }
    // endregion

    @Test
    @Transactional
    void testCreateLevel() {
        Template template = Template.builder().name("Test Template").description("A test template")
                .userId(UUID.randomUUID()).build();

        template = templateRepository.saveAndFlush(template);
        UUID templateId = template.getTemplateId();

        LevelCreationRequest request = LevelCreationRequest.builder().levelSequence(3).templateId(templateId.toString())
                .name("Test Template").build();

        LevelDTO level = service.createLevel(request);

        assertThat(level).isNotNull();
        assertThat(level.getLevelSequence()).isEqualTo(3);
    }

    @Test
    @Transactional
    void testCreateLevelNullError() {
        assertThatThrownBy(() -> service.createLevel(null)).isInstanceOf(AssertionError.class);
    }
    // endregion
}