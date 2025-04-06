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
 * A delegate to be called by the {@link SessionApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public interface SessionApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /session/{tags} : Get all escape-room instances having specific tags Retrieves a list of escape-room
     * instances filtered by tags
     *
     * @param tags
     *            List of tags to filter by (required)
     *
     * @return OK (status code 200)
     *
     * @see SessionApi#getERByTags
     */
    default ResponseEntity<List<EscapeRoomSessionResponse>> getERByTags(List<String> tags) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"room_pin\" : 420666, \"escape_room_template_id\" : \"c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"state\" : \"open\", \"escape_room_session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"play_time\" : 60, \"tags\" : [ \"[]\", \"[]\" ] }, { \"room_pin\" : 420666, \"escape_room_template_id\" : \"c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"state\" : \"open\", \"escape_room_session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"play_time\" : 60, \"tags\" : [ \"[]\", \"[]\" ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /session/{room_pin} : Retrieve an escape-room session by room pin Fetches an escape-room session using its
     * room pin
     *
     * @param roomPin
     *            The pin to join the escape-room (required)
     *
     * @return OK (status code 200)
     *
     * @see SessionApi#getERSessionByPin
     */
    default ResponseEntity<EscapeRoomSessionResponse> getERSessionByPin(Integer roomPin) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
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
