package at.escapedoom.data.service.rest.api;

import at.escapedoom.data.service.rest.config.ControllerTestConfig;
import at.escapedoom.data.service.rest.utils.TestActions;
import org.junit.jupiter.api.Test;

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
