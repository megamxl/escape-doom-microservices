package at.escapedoom.leaderboard.rest.api;

import at.escapedoom.leaderboard.rest.model.FullExportRoomPinGet200ResponseInner;
import at.escapedoom.leaderboard.rest.model.UserProgress;

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
@RequestMapping("${openapi.leaderboard.base-path:/v1/leaderboard}")
public class SessionLeaderboardApiController implements SessionLeaderboardApi {

    private final SessionLeaderboardApiDelegate delegate;

    public SessionLeaderboardApiController(@Autowired(required = false) SessionLeaderboardApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new SessionLeaderboardApiDelegate() {
        });
    }

    @Override
    public SessionLeaderboardApiDelegate getDelegate() {
        return delegate;
    }

}
