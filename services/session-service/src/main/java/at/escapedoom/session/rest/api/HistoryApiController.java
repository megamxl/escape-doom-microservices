package at.escapedoom.session.rest.api;

import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;

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
public class HistoryApiController implements HistoryApi {

    private final HistoryApiDelegate delegate;

    public HistoryApiController(@Autowired(required = false) HistoryApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new HistoryApiDelegate() {
        });
    }

    @Override
    public HistoryApiDelegate getDelegate() {
        return delegate;
    }

}
