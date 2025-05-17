package at.escapedoom.data.rest.api;

import at.escapedoom.data.config.ControllerTestConfig;
import at.escapedoom.data.config.SecurityTestConfig;
import at.escapedoom.data.rest.utils.TestActions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

@Import({ TemplateApiController.class, SecurityTestConfig.class })
class RiddleControllerMvcTest extends ControllerTestConfig {

    @Test
    void createRiddle_success() throws Exception {
        TestActions actions = new TestActions(mockMvc, objectMapper)
                .withTemplateApiDelegate(templateApiDelegate)
                .withLevelApiDelegate(levelApiDelegate)
                .withRiddleApiDelegate(riddleApiDelegate);

        String templateId = actions.createTemplate();
        String levelId = actions.createLevel(templateId);
        actions.createRiddle(levelId);
    }

    @Test
    void deleteRiddle_removesRiddleFromLevel() throws Exception {
        TestActions actions = new TestActions(mockMvc, objectMapper).withTemplateApiDelegate(templateApiDelegate)
                .withLevelApiDelegate(levelApiDelegate).withRiddleApiDelegate(riddleApiDelegate);

        String templateId = actions.createTemplate();
        String levelId = actions.createLevel(templateId);

        actions.createRiddle(levelId);
        String riddleId = "riddle001";

        actions.deleteRiddle(riddleId);

        actions.assertLevelHasNoRiddle(levelId);
    }

}
