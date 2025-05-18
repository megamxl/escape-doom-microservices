package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.TemplateCreateRequestDTO;
import at.escapedoom.data.rest.model.TemplateDTO;
import at.escapedoom.data.config.ControllerTestConfig;
import at.escapedoom.data.rest.asserts.Assertions;
import at.escapedoom.data.rest.factory.TemplateTestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TemplateControllerMvcTest extends ControllerTestConfig {

    @Test
    @WithMockUser(username = "lector", roles = { "LECTOR" })
    void createTemplate_success() throws Exception {
        TemplateCreateRequestDTO request = TemplateTestFactory.createRequest();
        TemplateDTO response = TemplateTestFactory.createResponseFrom(request);

        when(templateApiDelegate.createTemplate(any())).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(post("/v1/templates").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andExpectAll(Assertions.templateCreated());
    }
}
