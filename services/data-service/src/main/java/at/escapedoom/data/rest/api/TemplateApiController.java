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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
@Controller
@RequestMapping("${openapi.escapeDoomData.base-path:/v1/data}")
public class TemplateApiController implements TemplateApi {

    private final TemplateApiDelegate delegate;

    public TemplateApiController(@Autowired(required = false) TemplateApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new TemplateApiDelegate() {
        });
    }

    @Override
    public TemplateApiDelegate getDelegate() {
        return delegate;
    }

}
