package at.escapedoom.data.service;

import at.escapedoom.data.DataApi;
import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.entity.*;
import at.escapedoom.data.rest.model.NodeType;
import at.escapedoom.data.rest.model.SceneDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

;

@SpringBootTest(classes = DataApi.class)
@ActiveProfiles("test")
class SceneServiceTest {
    // TODO @Thommy Fix test

    // @Autowired
    // private SceneService service;
    // @Autowired
    // private SceneRepository repository;
    //
    // @Transactional
    //
    // @BeforeEach
    // void setup() {
    //
    // repository.deleteAllInBatch();
    // repository.flush();
    //
    // Scene scene = Scene.builder().sceneSequence(1)
    // .backgroundImageURI(String.valueOf(URI.create("https://example.com/background.png"))).name("Scene 1")
    // .build();
    //
    // List<Node> nodes = Arrays.asList(Node.builder().position(new Position(20.5, 40.0)).nodeType(NodeType.CONSOLE)
    // .nodeInfo(NodeInfo.builder().description("This is a console node")
    // .imageURI("https://example.com/image.png").title("I like cheese").build())
    // .scene(scene).build());
    //
    // scene.setNodes(nodes);
    //
    // VALID_SCENE_ID = repository.save(scene).getSceneId().toString();
    // }
    //
    // // region GET Tests
    //
    // @Test
    // void testGetAllScenes() {
    // List<SceneDTO> scenes = service.getAllScenes();
    //
    // assertEquals(1, scenes.size());
    // assertEquals(NodeType.CONSOLE, scenes.get(0).getNodes().get(0).getNodeType());
    // }
    // // endregion

}
