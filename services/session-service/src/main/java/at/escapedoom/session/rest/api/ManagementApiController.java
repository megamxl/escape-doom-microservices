package at.escapedoom.session.rest.api;

import at.escapedoom.session.rest.model.EscapeRoomCreation;
import at.escapedoom.session.rest.model.EscapeRoomState;
import at.escapedoom.session.rest.model.SessionResponse;
import java.util.UUID;

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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
@Controller
@RequestMapping("${openapi.session.base-path:/v1}")
public class ManagementApiController implements ManagementApi {

    private final ManagementApiDelegate delegate;

    public ManagementApiController(@Autowired(required = false) ManagementApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ManagementApiDelegate() {
        });
    }

    @Override
    public ManagementApiDelegate getDelegate() {
        return delegate;
    }

}
