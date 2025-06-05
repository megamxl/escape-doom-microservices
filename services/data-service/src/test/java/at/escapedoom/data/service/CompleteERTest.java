/*
 * package at.escapedoom.data.service;
 *
 * import at.escapedoom.data.rest.model.*; import at.escapedoom.data.config.PostgresTestConfig; import
 * at.escapedoom.data.utils.KeyCloakUtils; import jakarta.persistence.EntityManager; import
 * jakarta.persistence.PersistenceContext; import org.junit.jupiter.api.BeforeAll; import
 * org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.mockito.MockedStatic; import
 * org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest;
 * import org.springframework.test.context.ActiveProfiles;
 *
 * import java.util.Map; import java.util.NoSuchElementException; import java.util.UUID;
 *
 * import static org.junit.jupiter.api.Assertions.assertNotNull; import static
 * org.junit.jupiter.api.Assertions.assertThrows; import static org.mockito.Mockito.mockStatic;
 *
 * @SpringBootTest
 *
 * @ActiveProfiles("test") class CompleteERTest extends PostgresTestConfig {
 *
 * @Autowired TemplateService templateService;
 *
 * @Autowired LevelService levelService;
 *
 * @PersistenceContext private EntityManager entityManager;
 *
 * @Autowired SceneService sceneService;
 *
 * @Autowired RiddleService riddleService;
 *
 * @Autowired NodeService nodeService;
 *
 * private String templateId; private String levelId; private String sceneId; private String riddleId; private String
 * nodeId;
 *
 * private static final UUID MOCK_USER_ID = UUID.randomUUID(); private static MockedStatic<KeyCloakUtils>
 * mockedKeycloak;
 *
 * @BeforeAll static void init() { mockedKeycloak = mockStatic(KeyCloakUtils.class);
 * mockedKeycloak.when(KeyCloakUtils::getUserId).thenReturn(MOCK_USER_ID); }
 *
 * @BeforeEach void setUp() { TemplateCreateRequestDTO templateCreateRequestDTO =
 * TemplateCreateRequestDTO.builder().name("Test Template") .description("Test Description").build(); templateId =
 * templateService.createTemplate(templateCreateRequestDTO).getTemplateId();
 *
 * LevelCreationRequest levelCreationRequest = LevelCreationRequest.builder().name("Test Level")
 * .templateId(templateId).levelSequence(1).build(); levelId =
 * levelService.createLevel(levelCreationRequest).getLevelId();
 *
 * SceneRequestDTO sceneRequestDTO = SceneRequestDTO.builder().sceneSequence(1)
 * .backgroundImageUri("https://example.com/background.png").name("Scene 1").levelId(levelId).build(); sceneId =
 * sceneService.createScene(sceneRequestDTO).getSceneId();
 *
 * RiddleCreationRequestDTO riddleCreationRequestDTO = RiddleCreationRequestDTO.builder().levelId(levelId)
 * .language(CodingLanguage.JAVA).functionSignature("public static int sum(int a, int b)").input("2, 3")
 * .variableName("result").expectedOutput("42").build(); riddleId =
 * riddleService.createRiddle(riddleCreationRequestDTO).getRiddleId();
 *
 * final NodeCreationRequest nodeCreationRequest = NodeCreationRequest.builder().sceneId(sceneId)
 * .nodeSpecifics(NodeSpecificsDTO.builder().nodeType(NodeType.CONSOLE)
 * .additionalProperties(Map.of("return_description", "I am a console node return description", "constraints",
 * "Some funny constraints", "example", "Some example")) .build())
 * .description("This is a console node").title("Some funny title").position(new PositionDTO(20.5, 40.0)) .build();
 * nodeId = nodeService.createNode(nodeCreationRequest).getNodeId().toString(); }
 *
 * @Test void testComponentsExistAfterCreation() { assertNotNull(templateService.getTemplateById(templateId));
 * assertNotNull(levelService.getLevelById(levelId)); assertNotNull(sceneService.getSceneById(sceneId));
 * assertNotNull(riddleService.getRiddleById(riddleId)); assertNotNull(nodeService.getNodeById(nodeId)); }
 *
 * @Test void testCascadeDeleteTemplate() { templateService.deleteTemplate(UUID.fromString(templateId));
 * entityManager.clear();
 *
 * assertThrows(NoSuchElementException.class, () -> templateService.getTemplateById(templateId));
 * assertThrows(NoSuchElementException.class, () -> levelService.getLevelById(levelId));
 * assertThrows(NoSuchElementException.class, () -> sceneService.getSceneById(sceneId));
 * assertThrows(NoSuchElementException.class, () -> nodeService.getNodeById(nodeId));
 * assertThrows(NoSuchElementException.class, () -> riddleService.getRiddleById(riddleId)); }
 *
 * @Test void testCascadeDeleteLevel() { levelService.deleteLevel(levelId); entityManager.clear();
 *
 * assertThrows(NoSuchElementException.class, () -> levelService.getLevelById(levelId));
 * assertThrows(NoSuchElementException.class, () -> sceneService.getSceneById(sceneId));
 * assertThrows(NoSuchElementException.class, () -> riddleService.getRiddleById(riddleId));
 * assertThrows(NoSuchElementException.class, () -> nodeService.getNodeById(nodeId)); }
 *
 * @Test void testCascadeDeleteScene() { sceneService.deleteScene(sceneId); entityManager.clear();
 *
 * assertThrows(NoSuchElementException.class, () -> sceneService.getSceneById(sceneId));
 * assertThrows(NoSuchElementException.class, () -> nodeService.getNodeById(nodeId)); } }
 */
