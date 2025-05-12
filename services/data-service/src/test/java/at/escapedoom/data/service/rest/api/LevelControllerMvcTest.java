package at.escapedoom.data.service.rest.api;

import at.escapedoom.data.rest.model.TemplateDTO;
import at.escapedoom.data.service.rest.config.ControllerTestConfig;
import at.escapedoom.data.service.rest.factory.TemplateTestFactory;
import at.escapedoom.data.service.rest.utils.TestActions;
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

}
