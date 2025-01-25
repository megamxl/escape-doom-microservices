package at.escapedoom.session.rest.api;

import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;
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
 * A delegate to be called by the {@link TagsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public interface TagsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * PUT /tag/{escape_room_session_id}/{tag_name} : Add a tag to an escape-room instance
     * Add a tag to an escape-room instance
     *
     * @param escapeRoomSessionId The ID of the escape room session (required)
     * @param tagName The name of the tag to remove (required)
     * @return The tag was added to the escape room (status code 200)
     * @see TagsApi#addERTag
     */
    default ResponseEntity<EscapeRoomSessionResponse> addERTag(String escapeRoomSessionId,
        String tagName) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"room_pin\" : 420666, \"escape_room_template_id\" : \"c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"state\" : \"open\", \"escape_room_session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"play_time\" : 60, \"tags\" : [ \"[]\", \"[]\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /tag/{escape_room_session_id}/{tag_name} : Remove a tag from an escape-room instance
     * Remove a tag from an escape-room instance
     *
     * @param escapeRoomSessionId The ID of the escape room session (required)
     * @param tagName The name of the tag to remove (required)
     * @return The tag was added to the escape room (status code 200)
     * @see TagsApi#deleteERTag
     */
    default ResponseEntity<EscapeRoomSessionResponse> deleteERTag(String escapeRoomSessionId,
        String tagName) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"room_pin\" : 420666, \"escape_room_template_id\" : \"c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"state\" : \"open\", \"escape_room_session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"play_time\" : 60, \"tags\" : [ \"[]\", \"[]\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
