package at.escapedoom.data.rest.utils;

import at.escapedoom.data.rest.api.*;
import at.escapedoom.data.rest.asserts.Assertions;
import at.escapedoom.data.rest.factory.*;
import at.escapedoom.data.rest.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestActions {

    // === Dependencies ===
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    private TemplateApiDelegate templateApiDelegate;
    private LevelApiDelegate levelApiDelegate;
    private RiddleApiDelegate riddleApiDelegate;
    private SceneApiDelegate sceneApiDelegate;
    private NodeApiDelegate nodeApiDelegate;

    public TestActions(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    // === Fluent Setters for Delegates ===
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

    public TestActions withSceneApiDelegate(SceneApiDelegate delegate) {
        this.sceneApiDelegate = delegate;
        return this;
    }

    public TestActions withNodeApiDelegate(NodeApiDelegate delegate) {
        this.nodeApiDelegate = delegate;
        return this;
    }

    // === Template Actions ===
    public String createTemplate() throws Exception {
        TemplateCreateRequestDTO request = TemplateTestFactory.createRequest();
        TemplateDTO response = TemplateTestFactory.createResponseFrom(request);

        when(templateApiDelegate.createTemplate(any())).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(post("/v1/templates").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andExpectAll(Assertions.templateCreated());

        return response.getTemplateId();
    }

    public void assertTemplateHasNoLevels(String templateId, TemplateDTO original) throws Exception {
        TemplateDTO updated = TemplateDTO.builder().templateId(templateId).name(original.getName())
                .description(original.getDescription()).levels(List.of()).build();

        when(templateApiDelegate.getTemplate(templateId)).thenReturn(ResponseEntity.ok(updated));

        mockMvc.perform(get("/v1/templates/" + templateId)).andExpect(status().isOk())
                .andExpect(jsonPath("$.levels").isEmpty());
    }

    // === Level Actions ===
    public String createLevel(String templateId) throws Exception {
        LevelCreationRequest request = LevelTestFactory.createRequest(templateId);
        LevelDTO response = LevelTestFactory.createResponseFrom(request);

        when(levelApiDelegate.createLevel(any())).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(post("/v1/levels").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andExpectAll(Assertions.levelCreated(templateId));

        return response.getLevelId();
    }

    public String deleteLevel(String levelId) throws Exception {
        when(levelApiDelegate.deleteLevel(levelId)).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/v1/levels/" + levelId))
                .andExpect(status().isOk());

        return levelId;
    }

    public void assertLevelHasNoRiddle(String levelId) throws Exception {
        LevelDTO levelWithoutRiddle = LevelDTO.builder().levelId(levelId).name("Classroom").templateId("some-template")
                .levelSequence(1).riddle(null).build();

        when(levelApiDelegate.getLevel(levelId)).thenReturn(ResponseEntity.ok(levelWithoutRiddle));

        mockMvc.perform(get("/v1/levels/" + levelId)).andExpect(status().isOk())
                .andExpect(jsonPath("$.riddle").doesNotExist());
    }

    // === Riddle Actions ===
    public void createRiddle(String levelId) throws Exception {
        RiddleCreationRequestDTO request = RiddleTestFactory.createRequest(levelId);
        RiddleDTO response = RiddleTestFactory.createResponseFrom(request);

        when(riddleApiDelegate.createRiddle(any())).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(post("/v1/riddles").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andExpectAll(Assertions.riddleCreated(levelId));
    }

    public void deleteRiddle(String riddleId) throws Exception {
        when(riddleApiDelegate.deleteRiddle(riddleId))
                .thenReturn(ResponseEntity.ok(new RiddleDeletionResponseDTO("Riddle deleted successfully")));

        mockMvc.perform(delete("/v1/riddles/" + riddleId))
                .andExpect(status().isOk());
    }

    // === Scene Actions ===
    public String createScene(String levelId) throws Exception {
        SceneRequestDTO request = SceneTestFactory.createRequest(levelId);
        SceneDTO response = SceneTestFactory.createResponseFrom(request);

        when(sceneApiDelegate.createScene(any())).thenReturn(ResponseEntity.status(201).body(response));

        mockMvc.perform(post("/v1/scenes").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated());

        return response.getSceneId();
    }

    public void assertSceneHasNoNodes(String sceneId) throws Exception {
        SceneDTO updatedScene = SceneDTO.builder().sceneId(sceneId).name("Scene 1").levelId("someLevel")
                .sceneSequence(1).backgroundImageUri("https://example.com/background.png").nodes(List.of()).build();

        when(sceneApiDelegate.getSceneById(sceneId)).thenReturn(ResponseEntity.ok(updatedScene));

        mockMvc.perform(get("/v1/scenes/" + sceneId)).andExpect(status().isOk())
                .andExpect(jsonPath("$.nodes").isEmpty());
    }

    // === Node Actions ===
    public String createNode(String sceneId) throws Exception {
        NodeCreationRequest request = NodeTestFactory.createRequest(sceneId);
        NodeDTO response = NodeTestFactory.createResponseFrom(request);

        when(nodeApiDelegate.createNode(any())).thenReturn(ResponseEntity.status(201).body(response));

        mockMvc.perform(post("/v1/nodes").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated());

        return String.valueOf(response.getNodeId());
    }

    public void deleteNode(String nodeId) throws Exception {
        when(nodeApiDelegate.deleteNode(nodeId)).thenReturn(ResponseEntity.ok(
                new NodeDeletionResponseDTO("Node deleted successfully")));

        mockMvc.perform(delete("/v1/nodes/" + nodeId))
                .andExpect(status().isOk());
    }
}
