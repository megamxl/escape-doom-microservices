package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.CreateBadRequest;
import at.escapedoom.data.rest.model.CreateInternalServerError;
import at.escapedoom.data.rest.model.CreateNotFound;
import at.escapedoom.data.rest.model.DeleteLevelSuccess;
import at.escapedoom.data.rest.model.EscapeRoomLevel;
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
     * POST /level : Create a new level Create an EscapeRoomLevel independently of any template
     *
     * @param escapeRoomLevel
     *            The details of the new EscapeRoomLevel (required)
     *
     * @return Level created successfully (status code 201) or Bad Request (status code 400) or Internal Server Error
     *         (status code 500)
     *
     * @see LevelApi#createLevel
     */
    default ResponseEntity<EscapeRoomLevel> createLevel(EscapeRoomLevel escapeRoomLevel) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"riddles\" : [ { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" }, { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" } ], \"sequence\" : 1, \"scenes\" : [ { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"escape_room_level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\" }";
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
     * DELETE /level/{escape-room-level-id} : Delete a level Delete a EscapeRoomLevel by its ID
     *
     * @param escapeRoomLevelId
     *            The unique ID of the EscapeRoomLevel to delete (required)
     *
     * @return Level deleted successfully (status code 200) or Not Found (status code 404) or Internal Server Error
     *         (status code 500)
     *
     * @see LevelApi#deleteLevel
     */
    default ResponseEntity<DeleteLevelSuccess> deleteLevel(String escapeRoomLevelId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 200, \"message\" : \"Level deleted successfully\" }";
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
     * GET /all-levels : Retrieve all levels by a specific user Retrieve all levels by a specific user
     *
     * @return A list of levels (status code 200) or Internal Server Error (status code 500)
     *
     * @see LevelApi#getAllLevels
     */
    default ResponseEntity<List<EscapeRoomLevel>> getAllLevels() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"riddles\" : [ { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" }, { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" } ], \"sequence\" : 1, \"scenes\" : [ { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"escape_room_level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\" }, { \"riddles\" : [ { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" }, { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" } ], \"sequence\" : 1, \"scenes\" : [ { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"escape_room_level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\" } ]";
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
     * GET /levels/{escape-room-level-id} : Get details of a level Retrieve details of a specific EscapeRoomLevel by its
     * ID
     *
     * @param escapeRoomLevelId
     *            The unique ID of the EscapeRoomLevel (required)
     *
     * @return Level details (status code 200) or Not Found (status code 404) or Internal Server Error (status code 500)
     *
     * @see LevelApi#getLevel
     */
    default ResponseEntity<EscapeRoomLevel> getLevel(String escapeRoomLevelId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"riddles\" : [ { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" }, { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" } ], \"sequence\" : 1, \"scenes\" : [ { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"escape_room_level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\" }";
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
     * GET /levels/{escape-room-template-id} : Retrieve levels Retrieve all levels associated with a specific template
     *
     * @param escapeRoomTemplateId
     *            (required)
     *
     * @return Success (status code 200)
     *
     * @see LevelApi#getLevelByTemplate
     */
    default ResponseEntity<Void> getLevelByTemplate(String escapeRoomTemplateId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /level/overide/{escape-room-level-id} : Override a level Override the details of a EscapeRoomLevel
     *
     * @param escapeRoomLevelId
     *            The unique ID of the EscapeRoomLevel (required)
     * @param escapeRoomLevel
     *            The overriden details of the EscapeRoomLevel (required)
     *
     * @return Level updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status code
     *         404) or Internal Server Error (status code 500)
     *
     * @see LevelApi#putLevelOfTemplate
     */
    default ResponseEntity<EscapeRoomLevel> putLevelOfTemplate(String escapeRoomLevelId,
            EscapeRoomLevel escapeRoomLevel) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"riddles\" : [ { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" }, { \"escape_room_riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"expected_output\" : \"42\", \"type\" : \"InputStringCompareRiddle\" } ], \"sequence\" : 1, \"scenes\" : [ { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"name\" : \"Scene 1\", \"escape_room_sequence_id\" : \"1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"escape_room_level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\" }";
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
