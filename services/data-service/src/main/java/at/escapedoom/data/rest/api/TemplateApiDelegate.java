package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.CreateBadRequestDTO;
import at.escapedoom.data.rest.model.CreateInternalServerErrorDTO;
import at.escapedoom.data.rest.model.CreateNotFoundDTO;
import at.escapedoom.data.rest.model.EscapeRoomTemplateCreateRequestDTO;
import at.escapedoom.data.rest.model.EscapeRoomTemplateDTO;
import at.escapedoom.data.rest.model.EscapeRoomTemplateResultDTO;
import at.escapedoom.data.rest.model.EscapeRoomTemplateUpdateRequestDTO;
import at.escapedoom.data.rest.model.EscapeRoomTemplateUpdateResultDTO;
import at.escapedoom.data.rest.model.GetTemplateNotFoundDTO;
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
 * A delegate to be called by the {@link TemplateApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public interface TemplateApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /templates : Creates a new Template for Escape Doom Game Creates a new Template for EscapeRoom
     *
     * @param escapeRoomTemplateCreateRequestDTO
     *            Lectors ID + Name and Description for a Template (required)
     *
     * @return The basic template component (status code 200) or Bad Request (status code 400) or Internal Server Error
     *         (status code 500)
     *
     * @see TemplateApi#createTemplate
     */
    default ResponseEntity<EscapeRoomTemplateDTO> createTemplate(
            EscapeRoomTemplateCreateRequestDTO escapeRoomTemplateCreateRequestDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"user_id\" : \"8437ea27-f56f-43d1-a5a1-77e2a2b57e8e\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\", \"template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"levels\" : [ { \"sequence\" : 1, \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"sequence\" : 1, \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ] }";
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
     * DELETE /templates/{escape-room-template-id} : Deletes an EscapeRoomTemplate Deletes an EscapeRoomTemplate by its
     * unique ID
     *
     * @param escapeRoomTemplateId
     *            The unique ID of the EscapeRoomTemplate to delete (required)
     *
     * @return Operation result for EscapeRoomTemplate (status code 200) or Bad Request (status code 400) or Not Found
     *         (status code 404) or Internal Server Error (status code 500)
     *
     * @see TemplateApi#deleteTemplate
     */
    default ResponseEntity<EscapeRoomTemplateResultDTO> deleteTemplate(String escapeRoomTemplateId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"Operation successful\" }";
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

    /**
     * GET /templates : Get all EscapeRoomTemplates Retrieve a list of all existing EscapeRoomTemplates from a Lector
     *
     * @return A list of templates (status code 200) or Internal Server Error (status code 500)
     *
     * @see TemplateApi#getAllTemplates
     */
    default ResponseEntity<List<EscapeRoomTemplateDTO>> getAllTemplates() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"user_id\" : \"8437ea27-f56f-43d1-a5a1-77e2a2b57e8e\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\", \"template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"levels\" : [ { \"sequence\" : 1, \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"sequence\" : 1, \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ] }, { \"user_id\" : \"8437ea27-f56f-43d1-a5a1-77e2a2b57e8e\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\", \"template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"levels\" : [ { \"sequence\" : 1, \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"sequence\" : 1, \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ] } ]";
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
     * GET /templates/{escape-room-template-id} : Get a specific EscapeRoomTemplate by ID Retrieve details of a specific
     * EscapeRoomTemplate using its unique ID
     *
     * @param escapeRoomTemplateId
     *            The unique ID of the EscapeRoomTemplate (required)
     *
     * @return Details of the specified template (status code 200) or Template not found (status code 404) or Internal
     *         Server Error (status code 500)
     *
     * @see TemplateApi#getTemplate
     */
    default ResponseEntity<EscapeRoomTemplateDTO> getTemplate(String escapeRoomTemplateId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"user_id\" : \"8437ea27-f56f-43d1-a5a1-77e2a2b57e8e\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\", \"template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"levels\" : [ { \"sequence\" : 1, \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"sequence\" : 1, \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } }, { \"node_type\" : \"ZOOM\", \"node_info\" : { \"imageURI\" : \"https://example.com/image.png\", \"description\" : \"This is a story node\", \"title\" : \"I like cheese\" }, \"position\" : { \"top\" : 50.5, \"left\" : 22 } } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"sequence_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 404, \"message\" : \"EscapeRoomTemplate with the provided ID was not found\" }";
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
     * PUT /templates/{escape-room-template-id} : Overrides an existing EscapeRoomTemplate Override the name,
     * description, and levels of an existing EscapeRoomTemplate
     *
     * @param escapeRoomTemplateId
     *            The unique ID of the EscapeRoomTemplate to update (required)
     * @param escapeRoomTemplateUpdateRequestDTO
     *            The updated data for the template (required)
     *
     * @return Template updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status
     *         code 404) or Internal Server Error (status code 500)
     *
     * @see TemplateApi#putTemplate
     */
    default ResponseEntity<EscapeRoomTemplateUpdateResultDTO> putTemplate(String escapeRoomTemplateId,
            EscapeRoomTemplateUpdateRequestDTO escapeRoomTemplateUpdateRequestDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 200, \"message\" : \"Template updated successfully\" }";
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
