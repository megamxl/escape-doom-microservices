package at.escapedoom.data.service.rest.config;

import at.escapedoom.SecurityTestConfig;
import at.escapedoom.data.rest.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Import({ TemplateApiController.class, SecurityTestConfig.class })
@ActiveProfiles("test")
@WithMockUser(username = "lector", roles = { "LECTOR" })
public abstract class ControllerTestConfig extends PostgresConfig {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected TemplateApiDelegate templateApiDelegate;

    @MockBean
    protected LevelApiDelegate levelApiDelegate;

    @MockBean
    protected RiddleApiDelegate riddleApiDelegate;

    @MockBean
    protected SceneApiDelegate sceneApiDelegate;

    @MockBean
    protected NodeApiDelegate nodeApiDelegate;
}
