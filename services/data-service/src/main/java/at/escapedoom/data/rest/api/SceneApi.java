/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.11.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.CreateBadRequestDTO;
import at.escapedoom.data.rest.model.CreateInternalServerErrorDTO;
import at.escapedoom.data.rest.model.CreateNotFoundDTO;
import at.escapedoom.data.rest.model.DeleteLevelResponseDTO;
import at.escapedoom.data.rest.model.SceneDTO;
import at.escapedoom.data.rest.model.SceneRequestDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
@Validated
@Tag(name = "Scene", description = "the Scene API")
public interface SceneApi {

    default SceneApiDelegate getDelegate() {
        return new SceneApiDelegate() {
        };
    }

    /**
     * POST /scenes : Create a new scene Create a Scene independently of any level
     *
     * @param sceneRequestDTO
     *            The details of the new Scene (required)
     *
     * @return Scene created successfully (status code 201) or Bad Request (status code 400) or Internal Server Error
     *         (status code 500)
     */
    @Operation(operationId = "createScene", summary = "Create a new scene", description = "Create a Scene independently of any level", tags = {
            "Scene" }, responses = {
                    @ApiResponse(responseCode = "201", description = "Scene created successfully", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SceneDTO.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateBadRequestDTO.class)) }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateInternalServerErrorDTO.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/scenes", produces = { "application/json" }, consumes = {
            "application/json" })

    default ResponseEntity<SceneDTO> createScene(
            @Parameter(name = "SceneRequestDTO", description = "The details of the new Scene", required = true) @Valid @RequestBody SceneRequestDTO sceneRequestDTO) {
        return getDelegate().createScene(sceneRequestDTO);
    }

    /**
     * DELETE /scenes/{scene-id} : Delete a scene Delete a specific Scene by its ID
     *
     * @param sceneId
     *            The unique ID of the Scene (required)
     *
     * @return Scene deleted successfully (status code 200) or Not Found (status code 404) or Internal Server Error
     *         (status code 500)
     */
    @Operation(operationId = "deleteScene", summary = "Delete a scene", description = "Delete a specific Scene by its ID", tags = {
            "Scene" }, responses = {
                    @ApiResponse(responseCode = "200", description = "Scene deleted successfully", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteLevelResponseDTO.class)) }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateNotFoundDTO.class)) }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateInternalServerErrorDTO.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/scenes/{scene-id}", produces = { "application/json" })

    default ResponseEntity<DeleteLevelResponseDTO> deleteScene(
            @Parameter(name = "scene-id", description = "The unique ID of the Scene", required = true, in = ParameterIn.PATH) @PathVariable("scene-id") String sceneId) {
        return getDelegate().deleteScene(sceneId);
    }

    /**
     * GET /scenes : Get all scenes Retrieve all scenes that are not linked to any specific level
     *
     * @return A list of scenes (status code 200) or Internal Server Error (status code 500)
     */
    @Operation(operationId = "getAllScenes", summary = "Get all scenes", description = "Retrieve all scenes that are not linked to any specific level", tags = {
            "Scene" }, responses = { @ApiResponse(responseCode = "200", description = "A list of scenes", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SceneDTO.class))) }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateInternalServerErrorDTO.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/scenes", produces = { "application/json" })

    default ResponseEntity<List<SceneDTO>> getAllScenes(

    ) {
        return getDelegate().getAllScenes();
    }

    /**
     * GET /scenes/{scene-id} : Get details of a scene Retrieve details of a specific scene by its ID
     *
     * @param sceneId
     *            The unique ID of the Scene (required)
     *
     * @return Scene details (status code 200) or Not Found (status code 404) or Internal Server Error (status code 500)
     */
    @Operation(operationId = "getSceneById", summary = "Get details of a scene", description = "Retrieve details of a specific scene by its ID", tags = {
            "Scene" }, responses = { @ApiResponse(responseCode = "200", description = "Scene details", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SceneDTO.class)) }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateNotFoundDTO.class)) }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateInternalServerErrorDTO.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/scenes/{scene-id}", produces = { "application/json" })

    default ResponseEntity<SceneDTO> getSceneById(
            @Parameter(name = "scene-id", description = "The unique ID of the Scene", required = true, in = ParameterIn.PATH) @PathVariable("scene-id") String sceneId) {
        return getDelegate().getSceneById(sceneId);
    }

    /**
     * PUT /scenes/{scene-id} : Update a scene Update the details of a specific Scene
     *
     * @param sceneId
     *            The unique ID of the Scene (required)
     * @param sceneRequestDTO
     *            The updated details of the Scene (required)
     *
     * @return Scene updated successfully (status code 200) or Bad Request (status code 400) or Not Found (status code
     *         404) or Internal Server Error (status code 500)
     */
    @Operation(operationId = "updateScene", summary = "Update a scene", description = "Update the details of a specific Scene", tags = {
            "Scene" }, responses = {
                    @ApiResponse(responseCode = "200", description = "Scene updated successfully", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SceneDTO.class)) }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateBadRequestDTO.class)) }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateNotFoundDTO.class)) }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateInternalServerErrorDTO.class)) }) })
    @RequestMapping(method = RequestMethod.PUT, value = "/scenes/{scene-id}", produces = {
            "application/json" }, consumes = { "application/json" })

    default ResponseEntity<SceneDTO> updateScene(
            @Parameter(name = "scene-id", description = "The unique ID of the Scene", required = true, in = ParameterIn.PATH) @PathVariable("scene-id") String sceneId,
            @Parameter(name = "SceneRequestDTO", description = "The updated details of the Scene", required = true) @Valid @RequestBody SceneRequestDTO sceneRequestDTO) {
        return getDelegate().updateScene(sceneId, sceneRequestDTO);
    }

}
