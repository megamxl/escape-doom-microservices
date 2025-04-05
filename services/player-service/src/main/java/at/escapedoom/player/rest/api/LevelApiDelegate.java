package at.escapedoom.player.rest.api;

import at.escapedoom.player.rest.model.EscapeRoomResult;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import at.escapedoom.player.rest.model.LevelDTO;
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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
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
     * @see LevelApi#getLevelOfSessionByPlayerSessionID
     */
    default ResponseEntity<LevelDTO> getLevelOfSessionByPlayerSessionID(UUID playerSessionId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }";
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
     * @see LevelApi#getLevelResult
     */
    default ResponseEntity<EscapeRoomResult> getLevelResult(UUID playerSessionId) {
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
     * @see LevelApi#submitSolutionAttemptForCurrentLevel
     */
    default ResponseEntity<Void> submitSolutionAttemptForCurrentLevel(UUID playerSessionId,
            EscapeRoomSolutionSubmition escapeRoomSolutionSubmition) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
