package at.escapedoom.data.config;

import at.escapedoom.data.rest.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(username = "lector", roles = { "LECTOR" })
public abstract class ControllerTestConfig extends PostgresTestConfig {

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
