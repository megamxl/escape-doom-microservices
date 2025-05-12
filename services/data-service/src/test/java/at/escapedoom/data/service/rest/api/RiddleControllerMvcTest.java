package at.escapedoom.data.service.rest.api;

import at.escapedoom.data.service.rest.config.ControllerTestConfig;
import at.escapedoom.data.service.rest.utils.TestActions;
import org.junit.jupiter.api.Test;

class RiddleControllerMvcTest extends ControllerTestConfig {

    @Test
    void createRiddle_success() throws Exception {
        TestActions actions = new TestActions(mockMvc, objectMapper).withTemplateApiDelegate(templateApiDelegate)
                .withLevelApiDelegate(levelApiDelegate).withRiddleApiDelegate(riddleApiDelegate);

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
