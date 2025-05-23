/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.11.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package at.escapedoom.session.rest.api;

import at.escapedoom.session.rest.model.SessionResponse;
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
@Tag(name = "history", description = "the history API")
public interface HistoryApi {

    default HistoryApiDelegate getDelegate() {
        return new HistoryApiDelegate() {
        };
    }

    /**
     * GET /history : Get the history of all escape-room instances of a lector Get the history of all escape-room
     * instances of a lector
     *
     * @return OK (status code 200)
     */
    @Operation(operationId = "getERHistory", summary = "Get the history of all escape-room instances of a lector", description = "Get the history of all escape-room instances of a lector", tags = {
            "history" }, responses = { @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SessionResponse.class))) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/history", produces = { "application/json" })

    default ResponseEntity<List<SessionResponse>> getERHistory(

    ) {
        return getDelegate().getERHistory();
    }

}
