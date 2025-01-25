package at.escapedoom.player.rest.api;

import at.escapedoom.player.rest.model.EscapeRoomJoin;
import at.escapedoom.player.rest.model.EscapeRoomJoinResponse;
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
 * A delegate to be called by the {@link LobbyApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public interface LobbyApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * PUT /join : Join an escape-room instance Join an escape-room instance
     *
     * @param escapeRoomJoin
     *            The escape-room instance to join (required)
     *
     * @return OK (status code 200)
     *
     * @see LobbyApi#joinPut
     */
    default ResponseEntity<EscapeRoomJoinResponse> joinPut(EscapeRoomJoin escapeRoomJoin) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"player_session_id\" : \"78787878-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"player_name\" : \"Waschbär\", \"escape_room_state\" : \"open\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
