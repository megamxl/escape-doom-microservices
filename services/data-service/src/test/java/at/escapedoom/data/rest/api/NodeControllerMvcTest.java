package at.escapedoom.data.rest.api;

import at.escapedoom.data.config.ControllerTestConfig;
import at.escapedoom.data.config.SecurityTestConfig;
import at.escapedoom.data.rest.utils.TestActions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import({ TemplateApiController.class, SecurityTestConfig.class })
class NodeControllerMvcTest extends ControllerTestConfig {

    @Test
    void createNode_success() throws Exception {
        TestActions actions = new TestActions(mockMvc, objectMapper).withTemplateApiDelegate(templateApiDelegate)
                .withLevelApiDelegate(levelApiDelegate).withSceneApiDelegate(sceneApiDelegate)
                .withNodeApiDelegate(nodeApiDelegate);

        String templateId = actions.createTemplate();
        String levelId = actions.createLevel(templateId);

        String sceneId = actions.createScene(levelId);

        actions.createNode(sceneId);
    }

    @Test
    void deleteNode_removesNodeFromScene() throws Exception {
        TestActions actions = new TestActions(mockMvc, objectMapper).withTemplateApiDelegate(templateApiDelegate)
                .withLevelApiDelegate(levelApiDelegate).withSceneApiDelegate(sceneApiDelegate)
                .withNodeApiDelegate(nodeApiDelegate);

        String templateId = actions.createTemplate();
        String levelId = actions.createLevel(templateId);

        String sceneId = actions.createScene(levelId);
        String nodeId = actions.createNode(sceneId);

        actions.deleteNode(nodeId);

        actions.assertSceneHasNoNodes(sceneId);
    }
}
