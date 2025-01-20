package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.Riddle;
import at.escapedoom.data.rest.model.RiddlePostRequest;
import at.escapedoom.data.rest.model.RiddlesEscapeRoomRiddleIdDelete200Response;
import at.escapedoom.data.rest.model.TemplateCreatePost400Response;
import at.escapedoom.data.rest.model.TemplateCreatePost500Response;
import at.escapedoom.data.rest.model.TemplateDeleteEscapeRoomTemplateIdDelete404Response;
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
 * A delegate to be called by the {@link RiddleApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public interface RiddleApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /all-riddles : Get all riddles Retrieve all riddles that are not yet linked to any level
     *
     * @return A list of riddles (status code 200) or Internal Server Error (status code 500)
     *
     * @see RiddleApi#allRiddlesGet
     */
    default ResponseEntity<List<Riddle>> allRiddlesGet() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" }, { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" } ]";
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
     * POST /riddle : Create a new riddle Create a riddle without linking it to a specific level
     *
     * @param riddlePostRequest
     *            The details of the riddle to create (required)
     *
     * @return Riddle created successfully (status code 201) or Bad Request (status code 400) or Internal Server Error
     *         (status code 500)
     *
     * @see RiddleApi#riddlePost
     */
    default ResponseEntity<Riddle> riddlePost(RiddlePostRequest riddlePostRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" }";
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
     * DELETE /riddles/{escape-room-riddle-id} : Delete a riddle Delete a riddle that is not linked to any level
     *
     * @param escapeRoomRiddleId
     *            The unique ID of the riddle (required)
     *
     * @return Riddle deleted successfully (status code 200) or Not Found (status code 404) or Internal Server Error
     *         (status code 500)
     *
     * @see RiddleApi#riddlesEscapeRoomRiddleIdDelete
     */
    default ResponseEntity<RiddlesEscapeRoomRiddleIdDelete200Response> riddlesEscapeRoomRiddleIdDelete(
            String escapeRoomRiddleId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 200, \"message\" : \"Riddle deleted successfully\" }";
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
     * PUT /riddles/{escape-room-riddle-id} : Override a riddle Override the details of a riddle
     *
     * @param escapeRoomRiddleId
     *            The unique ID of the riddle (required)
     * @param riddlePostRequest
     *            The override details of the riddle (required)
     *
     * @return Riddle updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status code
     *         404) or Internal Server Error (status code 500)
     *
     * @see RiddleApi#riddlesEscapeRoomRiddleIdPut
     */
    default ResponseEntity<Riddle> riddlesEscapeRoomRiddleIdPut(String escapeRoomRiddleId,
            RiddlePostRequest riddlePostRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" }";
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
