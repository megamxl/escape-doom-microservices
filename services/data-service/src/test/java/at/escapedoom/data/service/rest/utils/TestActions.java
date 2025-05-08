package at.escapedoom.data.service.rest.utils;

import at.escapedoom.data.rest.api.LevelApiDelegate;
import at.escapedoom.data.rest.api.RiddleApiDelegate;
import at.escapedoom.data.rest.api.TemplateApiDelegate;
import at.escapedoom.data.rest.model.*;
import at.escapedoom.data.service.rest.asserts.Assertions;
import at.escapedoom.data.service.rest.factory.LevelTestFactory;
import at.escapedoom.data.service.rest.factory.RiddleTestFactory;
import at.escapedoom.data.service.rest.factory.TemplateTestFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestActions {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    private TemplateApiDelegate templateApiDelegate;
    private LevelApiDelegate levelApiDelegate;
    private RiddleApiDelegate riddleApiDelegate;



    public TestActions(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    public TestActions withTemplateApiDelegate(TemplateApiDelegate delegate) {
        this.templateApiDelegate = delegate;
        return this;
    }

    public TestActions withLevelApiDelegate(LevelApiDelegate delegate) {
        this.levelApiDelegate = delegate;
        return this;
    }

    public TestActions withRiddleApiDelegate(RiddleApiDelegate delegate) {
        this.riddleApiDelegate = delegate;
        return this;
    }

    public String createTemplate() throws Exception {
        TemplateCreateRequestDTO templateRequest = TemplateTestFactory.createRequest();
        TemplateDTO templateResponse = TemplateTestFactory.createResponseFrom(templateRequest);

        when(templateApiDelegate.createTemplate(any())).thenReturn(ResponseEntity.ok(templateResponse));

        mockMvc.perform(post("/v1/templates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(templateRequest)))
                .andExpect(status().isOk())
                .andExpectAll(Assertions.templateCreated());

        return templateResponse.getTemplateId();
    }

    public String createLevel(String templateId) throws Exception {
        LevelCreationRequest levelRequest = LevelTestFactory.createRequest(templateId);
        LevelDTO levelResponse = LevelTestFactory.createResponseFrom(levelRequest);

        when(levelApiDelegate.createLevel(any())).thenReturn(ResponseEntity.ok(levelResponse));

        mockMvc.perform(post("/v1/levels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(levelRequest)))
                .andExpect(status().isOk())
                .andExpectAll(Assertions.levelCreated(templateId));

        return levelResponse.getLevelId();
    }

    public String deleteLevel(String levelId) throws Exception {
        when(levelApiDelegate.deleteLevel(levelId)).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/v1/levels/" + levelId))
                .andExpect(status().isOk());

        return levelId;
    }

    public void assertTemplateHasNoLevels(String templateId, TemplateDTO original) throws Exception {
        TemplateDTO updatedTemplate = TemplateDTO.builder()
                .templateId(templateId)
                .name(original.getName())
                .description(original.getDescription())
                .levels(List.of())
                .build();

        when(templateApiDelegate.getTemplate(templateId)).thenReturn(ResponseEntity.ok(updatedTemplate));

        mockMvc.perform(get("/v1/templates/" + templateId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.levels").isEmpty());
    }

    public void createRiddle(String levelId) throws Exception {
        RiddleCreationRequestDTO request = RiddleTestFactory.createRequest(levelId);
        RiddleDTO response = RiddleTestFactory.createResponseFrom(request);

        when(riddleApiDelegate.createRiddle(any())).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(post("/v1/riddles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpectAll(Assertions.riddleCreated(levelId));

    }
}
