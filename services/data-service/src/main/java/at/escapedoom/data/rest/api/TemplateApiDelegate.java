package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.CreateBadRequest;
import at.escapedoom.data.rest.model.CreateInternalServerError;
import at.escapedoom.data.rest.model.CreateNotFound;
import at.escapedoom.data.rest.model.EscapeRoomTemplate;
import at.escapedoom.data.rest.model.EscapeRoomTemplateCreateRequest;
import at.escapedoom.data.rest.model.EscapeRoomTemplateDTO;
import at.escapedoom.data.rest.model.EscapeRoomTemplateResult;
import at.escapedoom.data.rest.model.EscapeRoomTemplateUpdateRequest;
import at.escapedoom.data.rest.model.EscapeRoomTemplateUpdateResult;
import at.escapedoom.data.rest.model.GetTemplateNotFound;
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
     * POST /template/create : Creates a new Template for Escape Doom Game Creates a new Template for EscapeRoom
     *
     * @param escapeRoomTemplateCreateRequest
     *            Lectors ID + Name and Description for a Template (required)
     *
     * @return Operation result for EscapeRoomTemplate (status code 200) or Bad Request (status code 400) or Internal
     *         Server Error (status code 500)
     *
     * @see TemplateApi#createTemplate
     */
    default ResponseEntity<EscapeRoomTemplateResult> createTemplate(
            EscapeRoomTemplateCreateRequest escapeRoomTemplateCreateRequest) {
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
                    String exampleString = "{ \"message\" : \"An unexpected error occurred on the server\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /template/delete/{escape-room-template-id} : Deletes an EscapeRoomTemplate Deletes an EscapeRoomTemplate
     * by its unique ID
     *
     * @param escapeRoomTemplateId
     *            The unique ID of the EscapeRoomTemplate to delete (required)
     *
     * @return Operation result for EscapeRoomTemplate (status code 200) or Bad Request (status code 400) or Not Found
     *         (status code 404) or Internal Server Error (status code 500)
     *
     * @see TemplateApi#deleteTemplate
     */
    default ResponseEntity<EscapeRoomTemplateResult> deleteTemplate(String escapeRoomTemplateId) {
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
     * GET /all-templates : Get all EscapeRoomTemplates Retrieve a list of all existing EscapeRoomTemplates from a
     * Lector
     *
     * @return A list of templates (status code 200) or Internal Server Error (status code 500)
     *
     * @see TemplateApi#getAllTemplates
     */
    default ResponseEntity<List<EscapeRoomTemplateDTO>> getAllTemplates() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"escape_room_template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\" }, { \"escape_room_template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\" } ]";
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
     * GET /template/{escape-room-template-id} : Get a specific EscapeRoomTemplate by ID Retrieve details of a specific
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
    default ResponseEntity<EscapeRoomTemplate> getTemplate(String escapeRoomTemplateId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"EscapeRoomLevel\" : [ ], \"escape_room_template_id\" : \"b6557071-e7fa-47bc-bdd1-5657ebd325b8\", \"name\" : \"SDE24\", \"description\" : \"Cäsar´s Rätsel\" }";
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
     * PUT /template/override/{escape-room-template-id} : Overrides an existing EscapeRoomTemplate Override the name,
     * description, and levels of an existing EscapeRoomTemplate
     *
     * @param escapeRoomTemplateId
     *            The unique ID of the EscapeRoomTemplate to update (required)
     * @param escapeRoomTemplateUpdateRequest
     *            The updated data for the template (required)
     *
     * @return Template updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status
     *         code 404) or Internal Server Error (status code 500)
     *
     * @see TemplateApi#putTemplate
     */
    default ResponseEntity<EscapeRoomTemplateUpdateResult> putTemplate(String escapeRoomTemplateId,
            EscapeRoomTemplateUpdateRequest escapeRoomTemplateUpdateRequest) {
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
