package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.CreateBadRequestDTO;
import at.escapedoom.data.rest.model.CreateInternalServerErrorDTO;
import at.escapedoom.data.rest.model.CreateNotFoundDTO;
import at.escapedoom.data.rest.model.DeleteLevelSuccessDTO;
import at.escapedoom.data.rest.model.LevelCreationRequest;
import at.escapedoom.data.rest.model.LevelDTO;
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
     * POST /levels : Create a new level Create an Level independently of any template
     *
     * @param levelCreationRequest
     *            The details of the new Level (required)
     *
     * @return Level created successfully (status code 201) or Bad Request (status code 400) or Internal Server Error
     *         (status code 500)
     *
     * @see LevelApi#createLevel
     */
    default ResponseEntity<LevelDTO> createLevel(LevelCreationRequest levelCreationRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Invalid data provided\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /levels/{level-id} : Delete a level Delete a Level by its ID
     *
     * @param levelId
     *            The unique ID of the Level to delete (required)
     *
     * @return Level deleted successfully (status code 200) or Not Found (status code 404) or Internal Server Error
     *         (status code 500)
     *
     * @see LevelApi#deleteLevel
     */
    default ResponseEntity<DeleteLevelSuccessDTO> deleteLevel(String levelId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 200, \"message\" : \"Level deleted successfully\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /levels : Retrieve all levels by a specific user Retrieve all levels by a specific user
     *
     * @return A list of levels (status code 200) or Internal Server Error (status code 500)
     *
     * @see LevelApi#getAllLevels
     */
    default ResponseEntity<List<LevelDTO>> getAllLevels() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /levels/{level-id} : Get details of a level Retrieve details of a specific Level by its ID
     *
     * @param levelId
     *            The unique ID of the Level (required)
     *
     * @return Level details (status code 200) or Not Found (status code 404) or Internal Server Error (status code 500)
     *
     * @see LevelApi#getLevel
     */
    default ResponseEntity<LevelDTO> getLevel(String levelId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /templates/{template-id}/levels : Get levels for a specific template Retrieve all levels associated with a
     * given template
     *
     * @param templateId
     *            (required)
     *
     * @return A list of levels (status code 200) or Not Found (status code 404) or Internal Server Error (status code
     *         500)
     *
     * @see LevelApi#getLevelsByTemplate
     */
    default ResponseEntity<List<LevelDTO>> getLevelsByTemplate(String templateId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /levels/{level-id} : Override a level Override the details of a Level
     *
     * @param levelId
     *            The unique ID of the Level (required)
     * @param levelDTO
     *            The overridden details of the Level (required)
     *
     * @return Level updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status code
     *         404) or Internal Server Error (status code 500)
     *
     * @see LevelApi#updateLevel
     */
    default ResponseEntity<LevelDTO> updateLevel(String levelId, LevelDTO levelDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Invalid data provided\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"The requested resource was not found\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
