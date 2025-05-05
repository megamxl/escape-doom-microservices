package at.escapedoom.data.service;

import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.NodeRepository;
import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.TemplateRepository;
import at.escapedoom.data.data.entity.ConsoleNodeSpecifics;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.rest.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class NodeServiceTest {

    private final UUID USER_ID = UUID.randomUUID();
    private final String INVALID_NODE_ID = UUID.randomUUID().toString();

    private UUID nodeId;
    private String sceneId;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private NodeRepository nodeRepository;

    @BeforeEach
    @Transactional
    void setUp() {
        nodeRepository.deleteAllInBatch();
        sceneRepository.deleteAllInBatch();
        levelRepository.deleteAllInBatch();
        templateRepository.deleteAllInBatch();
        templateRepository.flush();

        Template template = Template.builder().name("Test Template").description("Test Template").userId(USER_ID)
                .build();

        templateRepository.save(template);

        Level level = Level.builder().template(template).levelSequence(1).name("Classroom").build();

        var levelId = levelRepository.save(level).getLevelId();

        Scene scene = Scene.builder().sceneSequence(1).backgroundImageUri("https://example.com/background.png")
                .name("Scene 1").levelId(levelId).build();

        sceneId = sceneRepository.save(scene).getSceneId().toString();

        final NodeCreationRequest creationRequest = NodeCreationRequest.builder().sceneId(sceneId)
                .nodeSpecifics(NodeSpecificsDTO.builder().nodeType(NodeType.CONSOLE).build()).title("Something")
                .description("This is a console node").position(new PositionDTO(20.5, 40.0)).build();

        nodeId = nodeService.createNode(creationRequest).getNodeId();
    }

    @Test
    void testGetAllNodes() {
        List<NodeDTO> nodes = nodeService.getAllNodes();

        assertEquals(1, nodes.size());
        assertEquals(nodeId, nodes.getFirst().getNodeId());
    }

    @Test
    void testGetNodeById() {
        NodeDTO node = nodeService.getNodeById(nodeId.toString());

        assertEquals(nodeId, node.getNodeId());
        assertEquals(20.5, node.getPosition().getTopPercentage());
    }

    @Test
    @Transactional
    void testCreateNode() {

        final NodeCreationRequest creationRequest = NodeCreationRequest.builder().sceneId(sceneId)
                .nodeSpecifics(NodeSpecificsDTO.builder().nodeType(NodeType.CONSOLE).build())
                .description("Test test... 1. 2. 3.").title("If this fails we jails")
                .position(new PositionDTO(20.5, 40.0)).build();

        NodeDTO response = nodeService.createNode(creationRequest);

        assertNotEquals(nodeId, response.getNodeId());
        assertEquals(NodeType.STORY, response.getNodeSpecifics().getNodeType());
        assertEquals(sceneId, response.getSceneId().toString());
    }

    @Test
    @Transactional
    void testCreateNodeWithNullRequest() {
        assertThrows(AssertionError.class, () -> nodeService.createNode(null));
    }

    @Test
    @Transactional
    void testCreateNodeWithNullSceneId() {
        assertThrows(AssertionError.class, () -> nodeService.createNode(NodeCreationRequest.builder().build()));
    }

    @Test
    @Transactional
    void testUpdateNode() {

        NodeDTO updateNode = nodeService.getNodeById(nodeId.toString());

        final NodeType newType = NodeType.ZOOM;
        final double newTopPosition = 66.6;
        final String newTitle = "Lagerregal";

        updateNode.nodeSpecifics(NodeSpecificsDTO.builder().nodeType(newType).build());
        updateNode.getPosition().setTopPercentage(newTopPosition);
        updateNode.setTitle(newTitle);

        NodeDTO response = nodeService.updateNode(nodeId.toString(), updateNode);

        assertEquals(nodeId, response.getNodeId());
        assertEquals(newType, response.getNodeSpecifics().getNodeType());
        assertEquals(newTopPosition, response.getPosition().getTopPercentage());
        assertEquals(newTitle, response.getTitle());
    }

    @Test
    @Transactional
    void testUpdateNodeNullId() {
        assertThrows(AssertionError.class, () -> nodeService.updateNode(null, null));
    }

    @Test
    @Transactional
    void testUpdateNodeNullRequest() {
        assertThrows(AssertionError.class, () -> nodeService.updateNode(nodeId.toString(), null));
    }

    @Test
    @Transactional
    void testDeleteNode() {
        nodeService.deleteNode(nodeId.toString());
        assertEquals(0, nodeService.getAllNodes().size());
    }

    @Test
    @Transactional
    void testDeleteNodeInvalidNodeId() {
        assertThrows(NoSuchElementException.class, () -> nodeService.deleteNode(INVALID_NODE_ID));
        assertEquals(1, nodeService.getAllNodes().size());
    }

    @Test
    @Transactional
    void testDeleteNodeNullNodeId() {
        assertThrows(AssertionError.class, () -> nodeService.deleteNode(null));
        assertEquals(1, nodeService.getAllNodes().size());
    }
}
