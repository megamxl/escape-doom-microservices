package at.escapedoom.data.rest.api;

import at.escapedoom.data.rest.model.Riddle;
import at.escapedoom.data.rest.model.RiddlePostRequest;
import at.escapedoom.data.rest.model.RiddlesEscapeRoomRiddleIdDelete200Response;
import at.escapedoom.data.rest.model.TemplateCreatePost400Response;
import at.escapedoom.data.rest.model.TemplateCreatePost500Response;
import at.escapedoom.data.rest.model.TemplateDeleteEscapeRoomTemplateIdDelete404Response;


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
public class RiddleApiController implements RiddleApi {

    private final RiddleApiDelegate delegate;

    public RiddleApiController(@Autowired(required = false) RiddleApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new RiddleApiDelegate() {});
    }

    @Override
    public RiddleApiDelegate getDelegate() {
        return delegate;
    }

}
