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
@Tag(name = "session", description = "the session API")
public interface SessionApi {

    default SessionApiDelegate getDelegate() {
        return new SessionApiDelegate() {
        };
    }

    /**
     * GET /session/{pin} : Get escape-room session by room pin Fetches an escape-room session using its room pin
     *
     * @param pin
     *            The 6-digit room pin (required)
     *
     * @return OK (status code 200) or Not Found (status code 404)
     */
    @Operation(operationId = "getERSessionByPin", summary = "Get escape-room session by room pin", description = "Fetches an escape-room session using its room pin", tags = {
            "session" }, responses = { @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SessionResponse.class)) }),
                    @ApiResponse(responseCode = "404", description = "Not Found") })
    @RequestMapping(method = RequestMethod.GET, value = "/session/{pin}", produces = { "application/json" })

    default ResponseEntity<SessionResponse> getERSessionByPin(
            @Min(100000) @Max(999999) @Parameter(name = "pin", description = "The 6-digit room pin", required = true, in = ParameterIn.PATH) @PathVariable("pin") Integer pin) {
        return getDelegate().getERSessionByPin(pin);
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
     */
    @Operation(operationId = "getERSessionByTagOrPin", summary = "Get escape-room sessions by tag or pin", description = "Retrieves escape-room sessions filtered by a tag or a specific 6-digit room pin. Only one filter (tag or pin) should be used per request.", tags = {
            "session" }, responses = { @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SessionResponse.class))) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/session", produces = { "application/json" })

    default ResponseEntity<List<SessionResponse>> getERSessionByTagOrPin(
            @Parameter(name = "tag", description = "The tag to filter sessions by", in = ParameterIn.QUERY) @Valid @RequestParam(value = "tag", required = false) String tag,
            @Min(100000) @Max(999999) @Parameter(name = "pin", description = "The 6-digit room pin", in = ParameterIn.QUERY) @Valid @RequestParam(value = "pin", required = false) Integer pin) {
        return getDelegate().getERSessionByTagOrPin(tag, pin);
    }

}
