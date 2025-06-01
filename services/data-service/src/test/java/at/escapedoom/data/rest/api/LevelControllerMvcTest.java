package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.TemplateDTO;
import at.escapedoom.data.config.ControllerTestConfig;
import at.escapedoom.data.rest.factory.TemplateTestFactory;
import at.escapedoom.data.rest.utils.TestActions;
import org.junit.jupiter.api.Test;

class LevelControllerMvcTest extends ControllerTestConfig {

    @Test
    void createLevel_success() throws Exception {
        TestActions actions = new TestActions(mockMvc, objectMapper).withTemplateApiDelegate(templateApiDelegate)
                .withLevelApiDelegate(levelApiDelegate);

        String templateId = actions.createTemplate();
        actions.createLevel(templateId);
    }

    @Test
    void deleteLevel_removesLevelFromTemplate() throws Exception {
        TestActions actions = new TestActions(mockMvc, objectMapper).withTemplateApiDelegate(templateApiDelegate)
                .withLevelApiDelegate(levelApiDelegate);

        String templateId = actions.createTemplate();
        TemplateDTO templateResponse = TemplateTestFactory.createResponseFrom(TemplateTestFactory.createRequest());

        String levelId = actions.createLevel(templateId);
        actions.deleteLevel(levelId);
        actions.assertTemplateHasNoLevels(templateId, templateResponse);
    }

    @Test
    void updateLevel_inFullyBuiltTemplate_success() throws Exception {
        TestActions actions = new TestActions(mockMvc, objectMapper).withTemplateApiDelegate(templateApiDelegate)
                .withLevelApiDelegate(levelApiDelegate).withSceneApiDelegate(sceneApiDelegate);

        String templateId = actions.createTemplate();

        String levelId = actions.createLevel(templateId);

        actions.createScene(levelId);

        String updatedName = "Renovated Level";
        int updatedSequence = 42;

        actions.updateLevel(levelId, updatedName, updatedSequence);

        actions.assertLevelUpdated(levelId, updatedName, updatedSequence);
    }
}
