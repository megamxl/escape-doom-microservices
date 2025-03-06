package at.escapedoom.session.rest.api;

import at.escapedoom.session.rest.model.EscapeRoomCreation;
import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.session.rest.model.EscapeRoomState;
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
 * A delegate to be called by the {@link ManagementApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public interface ManagementApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /create : Create a new escape-room instance Creates a new escape-room instance
     *
     * @param escapeRoomCreation
     *            The escape-room template to use (required)
     *
     * @return OK (status code 200)
     *
     * @see ManagementApi#createERInstance
     */
    default ResponseEntity<EscapeRoomSessionResponse> createERInstance(EscapeRoomCreation escapeRoomCreation) {
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

    /**
     * PUT /state/{escape_room_session_id}/{state} : Start or stop an escape-room instance Starts or stops an
     * escape-room instance
     *
     * @param escapeRoomSessionId
     *            The id of the escape-room instance (required)
     * @param state
     *            The state to set (required)
     *
     * @return OK (status code 200)
     *
     * @see ManagementApi#toggleERInstanceState
     */
    default ResponseEntity<EscapeRoomSessionResponse> toggleERInstanceState(UUID escapeRoomSessionId,
            EscapeRoomState state) {
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
