package at.escapedoom.player.rest.api;

import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.rest.model.EscapeRoomResult;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import java.util.UUID;
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
 * A delegate to be called by the {@link LevelApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public interface LevelApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /level/{player_session_id} : Get the current level of the escape-room instance Get the current level of the
     * escape-room instance
     *
     * @param playerSessionId
     *            The session-id of the player (required)
     *
     * @return OK (status code 200) or Internal Server Error (status code 500)
     *
     * @see LevelApi#levelPlayerSessionIdGet
     */
    default ResponseEntity<EscapeRoomLevel> levelPlayerSessionIdGet(UUID playerSessionId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code_snippet\" : \"public static void main(String[] args) { public static string riddle1() { //Your code goes here! } }\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22.0 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22.0 } } ], \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22.0 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22.0 } } ], \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"level_sequence\" : 1 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /level/{player_session_id}/result : Get the result of the submitted solution for the current level of the
     * escape-room instance Get the result of the submitted solution for the current level of the escape-room instance
     *
     * @param playerSessionId
     *            The session-id of the player (required)
     *
     * @return OK (status code 200)
     *
     * @see LevelApi#levelPlayerSessionIdResultGet
     */
    default ResponseEntity<EscapeRoomResult> levelPlayerSessionIdResultGet(UUID playerSessionId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"output\" : \"Hello World!\", \"status\" : \"SUCCESS\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /level/{player_session_id}/submit : Submit a possible solution for the current level of the escape-room
     * instance Submit a possible solution for the current level of the escape-room instance
     *
     * @param playerSessionId
     *            The session-id of the player (required)
     * @param escapeRoomSolutionSubmition
     *            The solution to submit (required)
     *
     * @return OK (status code 200)
     *
     * @see LevelApi#levelPlayerSessionIdSubmitPost
     */
    default ResponseEntity<Void> levelPlayerSessionIdSubmitPost(UUID playerSessionId,
            EscapeRoomSolutionSubmition escapeRoomSolutionSubmition) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
