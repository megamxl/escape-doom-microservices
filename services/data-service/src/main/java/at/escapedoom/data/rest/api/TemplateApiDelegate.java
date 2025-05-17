package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.*;
import jakarta.annotation.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

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
     * POST /templates : Creates a new Template for Escape Doom Game Creates a new Template for
     *
     * @param templateCreateRequestDTO
     *            Lectors ID + Name and Description for a Template (required)
     *
     * @return The basic template component (status code 200) or Bad Request (status code 400) or Internal Server Error
     *         (status code 500)
     *
     * @see TemplateApi#createTemplate
     */
    default ResponseEntity<TemplateDTO> createTemplate(TemplateCreateRequestDTO templateCreateRequestDTO) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"user_id\" : \"8437ea27-f56f-43d1-a5a1-77e2a2b57e8e\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\", \"template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"levels\" : [ { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ] }";
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
     * DELETE /templates/{template-id} : Deletes an Template Deletes an Template by its unique ID
     *
     * @param templateId
     *            The unique ID of the Template to delete (required)
     *
     * @return Operation result for Template (status code 200) or Bad Request (status code 400) or Not Found (status
     *         code 404) or Internal Server Error (status code 500)
     *
     * @see TemplateApi#deleteTemplate
     */
    default ResponseEntity<TemplateResultDTO> deleteTemplate(String templateId) {
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
     * GET /templates : Get all Templates Retrieve a list of all existing Templates from a Lector
     *
     * @return A list of templates (status code 200) or Internal Server Error (status code 500)
     *
     * @see TemplateApi#getAllTemplates
     */
    default ResponseEntity<List<TemplateDTO>> getAllTemplates() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"user_id\" : \"8437ea27-f56f-43d1-a5a1-77e2a2b57e8e\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\", \"template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"levels\" : [ { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ] }, { \"user_id\" : \"8437ea27-f56f-43d1-a5a1-77e2a2b57e8e\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\", \"template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"levels\" : [ { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ] } ]";
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
     * GET /templates/{template-id} : Get a specific Template by ID Retrieve details of a specific Template using its
     * unique ID
     *
     * @param templateId
     *            The unique ID of the Template (required)
     *
     * @return Details of the specified template (status code 200) or Template not found (status code 404) or Internal
     *         Server Error (status code 500)
     *
     * @see TemplateApi#getTemplate
     */
    default ResponseEntity<TemplateDTO> getTemplate(String templateId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"user_id\" : \"8437ea27-f56f-43d1-a5a1-77e2a2b57e8e\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\", \"template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"levels\" : [ { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } }, { \"level_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"name\" : \"Classroom\", \"scenes\" : [ { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" }, { \"scene_sequence\" : 1, \"nodes\" : [ { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"scene_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"description\" : \"This is a story node\", \"position\" : { \"left_percentage\" : 22, \"top_percentage\" : 50.5 }, \"title\" : \"I like cheese\", \"node_specifics\" : { \"node_type\" : \"ZOOM\" }, \"node_id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"scene_id\" : \"241a70fe-47d6-4756-9ac7-330f1b199e84\", \"name\" : \"Scene 1\", \"background_image_uri\" : \"https://example.com/background.png\" } ], \"template_id\" : \"d1087efe-41fd-42da-9110-62d35659cf1f\", \"level_sequence\" : 1, \"riddle\" : { \"function_signature\" : \"public static int sum(int a, int b)\", \"input\" : \"2, 3\", \"riddle_id\" : \"5830daed-cb7f-47dd-8248-5dee9bf0aa3d\", \"level_id\" : \"a12b34c5-6789-4def-abcd-12345678abcd\", \"expected_output\" : \"42\", \"language\" : \"JAVA\", \"variable_name\" : \"result\" } } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 404, \"message\" : \"Template with the provided ID was not found\" }";
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
     * PUT /templates/{template-id} : Overrides an existing Template Override the name, description, and levels of an
     * existing Template
     *
     * @param templateId
     *            The unique ID of the Template to update (required)
     * @param templateUpdateRequestDTO
     *            The updated data for the template (required)
     *
     * @return Template updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status
     *         code 404) or Internal Server Error (status code 500)
     *
     * @see TemplateApi#putTemplate
     */
    default ResponseEntity<TemplateUpdateResultDTO> putTemplate(String templateId,
            TemplateUpdateRequestDTO templateUpdateRequestDTO) {
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
