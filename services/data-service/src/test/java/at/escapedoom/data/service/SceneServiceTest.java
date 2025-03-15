package at.escapedoom.data.service;

import at.escapedoom.data.DataApi;
import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.TemplateRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.rest.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DataApi.class)
@ActiveProfiles("test")
class SceneServiceTest {

    private final UUID USER_ID = UUID.randomUUID();
    private final String INVALID_SCENE_ID = UUID.randomUUID().toString();

    private String sceneId;
    private UUID LEVEL_ID;

    @Autowired
    private SceneService service;
    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private LevelRepository levelRepository;

    @BeforeEach
    void setup() {
        sceneRepository.deleteAllInBatch();
        levelRepository.deleteAllInBatch();
        templateRepository.deleteAllInBatch();
        templateRepository.flush();

        Template template = Template.builder().name("Test Template").description("Test Template").userId(USER_ID)
                .build();

        templateRepository.save(template);

        Level level = Level.builder().template(template).templateId(template.getTemplateId()).levelSequence(1).build();

        LEVEL_ID = levelRepository.save(level).getLevelId();

        Scene scene = Scene.builder().sceneSequence(1).backgroundImageUri("https://example.com/background.png")
                .name("Scene 1").levelId(LEVEL_ID).build();

        sceneId = sceneRepository.save(scene).getSceneId().toString();
    }

    // region GET Tests

    @Test
    void testGetAllScenes() {
        List<SceneDTO> scenes = service.getAllScenes();

        assertEquals(1, scenes.size());
        assertEquals(sceneId, scenes.getFirst().getSceneId());
    }

    @Test
    void testGetSceneById() {
        SceneDTO scene = service.getSceneById(sceneId);

        assertEquals(sceneId, scene.getSceneId());
        assertEquals(LEVEL_ID.toString(), scene.getLevelId());
    }

    @Test
    void testGetSceneByIdNotFoundError() {
        assertThrows(IllegalArgumentException.class, () -> service.getSceneById(INVALID_SCENE_ID));
    }

    @Test
    void testGetSceneByIdNullError() {
        assertThrows(AssertionError.class, () -> service.getSceneById(null));
    }
    // endregion

    // region POST Tests
    @Test
    @Transactional
    void testCreateScene() {
        final int SCENE_SEQUENCE = 2;
        final String BG_IMAGE = "https://example.com/background.png";
        final String NAME = "Test Scene";

        SceneRequestDTO requestDTO = SceneRequestDTO.builder().sceneSequence(SCENE_SEQUENCE)
                .levelId(LEVEL_ID.toString()).backgroundImageUri(BG_IMAGE).name(NAME).build();

        SceneDTO creationResponse = service.createScene(requestDTO);

        assertEquals(SCENE_SEQUENCE, creationResponse.getSceneSequence());
        assertEquals(LEVEL_ID.toString(), creationResponse.getLevelId());
    }

    @Test
    @Transactional
    void testCreateSceneDuplicateSequenceError() {
        final int SCENE_SEQUENCE = 1;
        final String BG_IMAGE = "https://example.com/background.png";
        final String NAME = "Test Scene";

        SceneRequestDTO requestDTO = SceneRequestDTO.builder().sceneSequence(SCENE_SEQUENCE)
                .levelId(LEVEL_ID.toString()).backgroundImageUri(BG_IMAGE).name(NAME).build();

        assertThrows(DataIntegrityViolationException.class, () -> service.createScene(requestDTO));
    }

    @Test
    void testCreateSceneDTOIsNullError() {
        assertThrows(AssertionError.class, () -> service.createScene(null));
    }
    // endregion

    // region PUT Tests
    @Test
    @Transactional
    void testUpdateScene() {
        SceneRequestDTO sceneRequest = createSceneRequestDTO(null);

        SceneDTO updateResponse = service.updateScene(sceneId, sceneRequest);

        assertEquals(sceneRequest.getSceneSequence(), updateResponse.getSceneSequence());
        assertEquals(sceneRequest.getName(), updateResponse.getName());
    }

    @Test
    @Transactional
    void testUpdateSceneDuplicateSequenceError() {

        String uuid = service.createScene(createSceneRequestDTO(2)).getSceneId();

        SceneRequestDTO sceneRequest = createSceneRequestDTO(1);

        assertThrows(DataIntegrityViolationException.class, () -> service.updateScene(uuid, sceneRequest));
    }

    @Test
    @Transactional
    void testUpdateSceneDTOIsNullError() {
        assertThrows(AssertionError.class, () -> service.updateScene(null, null));
    }

    // endregion

    // region DELETE Tests
    @Test
    @Transactional
    void testDeleteScene() {

        service.deleteScene(sceneId);

        assertEquals(0, sceneRepository.count());
    }

    @Test
    @Transactional
    void testDeleteSceneNullError() {
        assertThrows(AssertionError.class, () -> service.deleteScene(null));
    }

    @Test
    @Transactional
    void testDeleteSceneInvalidUUIDError() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteScene("-1"));
    }

    @Test
    @Transactional
    void testDeleteSceneNotFound() {
        assertDoesNotThrow(() -> service.deleteScene(INVALID_SCENE_ID));
    }
    // endregion

    // region Utils
    private SceneRequestDTO createSceneRequestDTO(Integer sceneSequence) {
        int SCENE_SEQUENCE = sceneSequence != null ? sceneSequence : 2;
        String BG_IMAGE = "https://example.com/background.png";
        String NAME = "Updated Test Scene";

        SceneRequestDTO requestDTO = SceneRequestDTO.builder().sceneSequence(SCENE_SEQUENCE)
                .levelId(LEVEL_ID.toString()).backgroundImageUri(BG_IMAGE).name(NAME).build();

        return requestDTO;
    }
    // endregion

}
