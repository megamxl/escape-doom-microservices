package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.CreateBadRequest;
import at.escapedoom.data.rest.model.CreateInternalServerError;
import at.escapedoom.data.rest.model.CreateNotFound;
import at.escapedoom.data.rest.model.DeleteLevelResponse;
import at.escapedoom.data.rest.model.Scene;
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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public interface SceneApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /scene : Create a new scene Create a Scene independently of any level
     *
     * @param scene
     *            The details of the new Scene (required)
     *
     * @return Scene created successfully (status code 201) or Bad Request (status code 400) or Internal Server Error
     *         (status code 500)
     *
     * @see SceneApi#createScene
     */
    default ResponseEntity<Scene> createScene(Scene scene) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 400, \"message\" : \"Invalid data provided\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 500, \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /scenes/{escape-room-scene-id} : Delete a scene Delete a specific Scene by its ID
     *
     * @param escapeRoomSceneId
     *            The unique ID of the Scene (required)
     *
     * @return Scene deleted successfully (status code 200) or Not Found (status code 404) or Internal Server Error
     *         (status code 500)
     *
     * @see SceneApi#deleteScene
     */
    default ResponseEntity<DeleteLevelResponse> deleteScene(String escapeRoomSceneId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 200, \"message\" : \"Scene deleted successfully\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 404, \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 500, \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /scene : Get all scenes Retrieve all scenes that are not linked to any specific level
     *
     * @return A list of scenes (status code 200) or Internal Server Error (status code 500)
     *
     * @see SceneApi#getAllScenes
     */
    default ResponseEntity<List<Scene>> getAllScenes() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 500, \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /scenes/{escape-room-scene-id} : Get details of a scene Retrieve details of a specific scene by its ID
     *
     * @param escapeRoomSceneId
     *            The unique ID of the Scene (required)
     *
     * @return Scene details (status code 200) or Not Found (status code 404) or Internal Server Error (status code 500)
     *
     * @see SceneApi#getScene
     */
    default ResponseEntity<Scene> getScene(String escapeRoomSceneId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 404, \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 500, \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /scenes/{escape-room-scene-id} : Update a scene Update the details of a specific Scene
     *
     * @param escapeRoomSceneId
     *            The unique ID of the Scene (required)
     * @param scene
     *            The updated details of the Scene (required)
     *
     * @return Scene updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status code
     *         404) or Internal Server Error (status code 500)
     *
     * @see SceneApi#putScene
     */
    default ResponseEntity<Scene> putScene(String escapeRoomSceneId, Scene scene) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 400, \"message\" : \"Invalid data provided\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 404, \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 500, \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
