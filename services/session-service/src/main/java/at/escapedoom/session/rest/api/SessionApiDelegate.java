package at.escapedoom.session.rest.api;

import at.escapedoom.session.rest.model.SessionResponse;
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
     * GET /session/{pin} : Get escape-room session by room pin Fetches an escape-room session using its room pin
     *
     * @param pin
     *            The 6-digit room pin (required)
     *
     * @return OK (status code 200) or Not Found (status code 404)
     *
     * @see SessionApi#getERSessionByPin
     */
    default ResponseEntity<SessionResponse> getERSessionByPin(Integer pin) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"start_time\" : \"2025-04-06T14:30:00Z\", \"room_pin\" : 420666, \"end_time\" : \"2025-04-06T15:30:00Z\", \"session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"created_at\" : \"2025-04-06T14:20:00Z\", \"template_id\" : \"c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"state\" : \"open\", \"play_time\" : 60, \"tags\" : [ \"[]\", \"[]\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /session : Get escape-room sessions by tag or pin Retrieves escape-room sessions filtered by a tag or a
     * specific 6-digit room pin. Only one filter (tag or pin) should be used per request.
     *
     * @param tag
     *            The tag to filter sessions by (optional)
     * @param pin
     *            The 6-digit room pin (optional)
     *
     * @return OK (status code 200)
     *
     * @see SessionApi#getERSessionByTagOrPin
     */
    default ResponseEntity<List<SessionResponse>> getERSessionByTagOrPin(String tag, Integer pin) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"start_time\" : \"2025-04-06T14:30:00Z\", \"room_pin\" : 420666, \"end_time\" : \"2025-04-06T15:30:00Z\", \"session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"created_at\" : \"2025-04-06T14:20:00Z\", \"template_id\" : \"c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"state\" : \"open\", \"play_time\" : 60, \"tags\" : [ \"[]\", \"[]\" ] }, { \"start_time\" : \"2025-04-06T14:30:00Z\", \"room_pin\" : 420666, \"end_time\" : \"2025-04-06T15:30:00Z\", \"session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"created_at\" : \"2025-04-06T14:20:00Z\", \"template_id\" : \"c7a1c8d0-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"state\" : \"open\", \"play_time\" : 60, \"tags\" : [ \"[]\", \"[]\" ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
