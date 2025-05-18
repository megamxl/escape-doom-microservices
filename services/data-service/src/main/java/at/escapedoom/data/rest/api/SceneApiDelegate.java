package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.CreateBadRequestDTO;
import at.escapedoom.data.rest.model.CreateInternalServerErrorDTO;
import at.escapedoom.data.rest.model.CreateNotFoundDTO;
import at.escapedoom.data.rest.model.DeleteLevelResponseDTO;
import at.escapedoom.data.rest.model.SceneDTO;
import at.escapedoom.data.rest.model.SceneRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link SceneApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public interface SceneApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /scenes : Create a new scene Create a Scene independently of any level
     *
     * @param sceneRequestDTO
     *            The details of the new Scene (required)
     *
     * @return Scene created successfully (status code 201) or Bad Request (status code 400) or Internal Server Error
     *         (status code 500)
     *
     * @see SceneApi#createScene
     */
    default ResponseEntity<SceneDTO> createScene(SceneRequestDTO sceneRequestDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Invalid data provided\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /scenes/{scene-id} : Delete a scene Delete a specific Scene by its ID
     *
     * @param sceneId
     *            The unique ID of the Scene (required)
     *
     * @return Scene deleted successfully (status code 200) or Not Found (status code 404) or Internal Server Error
     *         (status code 500)
     *
     * @see SceneApi#deleteScene
     */
    default ResponseEntity<DeleteLevelResponseDTO> deleteScene(String sceneId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Scene deleted successfully\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /scenes : Get all scenes Retrieve all scenes that are not linked to any specific level
     *
     * @return A list of scenes (status code 200) or Internal Server Error (status code 500)
     *
     * @see SceneApi#getAllScenes
     */
    default ResponseEntity<List<SceneDTO>> getAllScenes() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /scenes/{scene-id} : Get details of a scene Retrieve details of a specific scene by its ID
     *
     * @param sceneId
     *            The unique ID of the Scene (required)
     *
     * @return Scene details (status code 200) or Not Found (status code 404) or Internal Server Error (status code 500)
     *
     * @see SceneApi#getSceneById
     */
    default ResponseEntity<SceneDTO> getSceneById(String sceneId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /scenes/{scene-id} : Update a scene Update the details of a specific Scene
     *
     * @param sceneId
     *            The unique ID of the Scene (required)
     * @param sceneRequestDTO
     *            The updated details of the Scene (required)
     *
     * @return Scene updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status code
     *         404) or Internal Server Error (status code 500)
     *
     * @see SceneApi#updateScene
     */
    default ResponseEntity<SceneDTO> updateScene(String sceneId, SceneRequestDTO sceneRequestDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Invalid data provided\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
