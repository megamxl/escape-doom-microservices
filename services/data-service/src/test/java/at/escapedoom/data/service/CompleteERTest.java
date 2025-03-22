package at.escapedoom.data.service;

import at.escapedoom.data.rest.model.*;
import at.escapedoom.data.utils.KeyCloakUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@ActiveProfiles("test")
public class CompleteERTest {

    @Autowired
    TemplateService templateService;

    @Autowired
    LevelService levelService;

    @Autowired
    SceneService sceneService;

    @Autowired
    RiddleService riddleService;

    @Autowired
    NodeService nodeService;

    private String templateId;
    private String levelId;
    private String sceneId;
    private String riddleId;
    private String nodeId;

    private static final UUID MOCK_USER_ID = UUID.randomUUID();
    private static MockedStatic<KeyCloakUtils> mockedKeycloak;


    @BeforeAll
    static void init() {
        mockedKeycloak = mockStatic(KeyCloakUtils.class);
        mockedKeycloak.when(KeyCloakUtils::getUserId).thenReturn(MOCK_USER_ID);
    }

    @Transactional
    @BeforeEach
    void setUp() {
        TemplateCreateRequestDTO templateCreateRequestDTO = TemplateCreateRequestDTO.builder()
                .name("Test Template")
                .description("Test Description")
                .build();
        templateId = templateService.createTemplate(templateCreateRequestDTO).getTemplateId();

        LevelCreationRequest levelCreationRequest = LevelCreationRequest.builder()
                .templateId(templateId)
                .levelSequence(1)
                .build();
         levelId = levelService.createLevel(levelCreationRequest).getLevelId();

        SceneRequestDTO sceneRequestDTO = SceneRequestDTO.builder()
                .sceneSequence(1)
                .backgroundImageUri("https://example.com/background.png")
                .name("Scene 1")
                .levelId(levelId)
                .build();
        sceneId = sceneService.createScene(sceneRequestDTO).getSceneId();

        RiddleCreationRequestDTO riddleCreationRequestDTO = RiddleCreationRequestDTO.builder()
                .levelId(levelId)
                .language(CodingLanguage.JAVA)
                .functionSignature("public static int sum(int a, int b)")
                .input("2, 3")
                .variableName("result")
                .expectedOutput("42")
                .build();
        riddleId = riddleService.createRiddle(riddleCreationRequestDTO).getRiddleId();

        final NodeCreationRequest nodeCreationRequest = NodeCreationRequest.builder().sceneId(sceneId)
                .nodeType(NodeType.CONSOLE)
                .nodeInfo(NodeInfoDTO.builder().description("This is a console node").title("Something")
                        .imageURI("https://example.com/background.png").build())
                .position(new PositionDTO(20.5, 40.0)).build();
        nodeId = nodeService.createNode(nodeCreationRequest).getNodeId().toString();
    }

    @Test
    @Transactional
    void testComponentsExistAfterCreation() {
        assertNotNull(templateService.getTemplateById(templateId));
        assertNotNull(levelService.getLevelById(levelId));
        assertNotNull(sceneService.getSceneById(sceneId));
        assertNotNull(riddleService.getRiddleById(riddleId));
        assertNotNull(nodeService.getNodeById(nodeId));
    }

    @Test
    @Transactional
    void testCascadeDeleteTemplate() {
        templateService.deleteTemplate(UUID.fromString(templateId));

        assertThrows(AssertionError.class, () -> templateService.getTemplateById(templateId));
        assertThrows(NoSuchElementException.class, () -> levelService.getLevelById(levelId));
        assertThrows(NoSuchElementException.class, () -> sceneService.getSceneById(sceneId));
        assertThrows(NoSuchElementException.class, () -> riddleService.getRiddleById(riddleId));
        assertThrows(NoSuchElementException.class, () -> nodeService.getNodeById(nodeId));
    }

    @Test
    @Transactional
    void testCascadeDeleteLevel() {
        levelService.deleteLevel(levelId);

        assertThrows(Exception.class, () -> levelService.getLevelById(levelId));
        assertThrows(Exception.class, () -> sceneService.getSceneById(sceneId));
        assertThrows(Exception.class, () -> riddleService.getRiddleById(riddleId));
        assertThrows(Exception.class, () -> nodeService.getNodeById(nodeId));
    }

    @Test
    @Transactional
    void testCascadeDeleteScene() {
        sceneService.deleteScene(sceneId);

        assertThrows(Exception.class, () -> sceneService.getSceneById(sceneId));
        assertThrows(Exception.class, () -> nodeService.getNodeById(nodeId));
    }
}
