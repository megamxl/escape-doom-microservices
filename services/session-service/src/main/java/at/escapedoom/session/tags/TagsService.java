package at.escapedoom.session.tags;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.api.TagsApiDelegate;
import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.session.service.EscapeRoomSessionService;
import at.escapedoom.session.util.KeycloakUserUtil;
import at.escapedoom.session.util.EscapeRoomSessionMapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;

@Component
public class TagsService implements TagsApiDelegate {

    private final EscapeRoomSessionService sessionService;

    public TagsService(EscapeRoomSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return TagsApiDelegate.super.getRequest();
    }
    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<EscapeRoomSessionResponse> addERTag(String escapeRoomSessionId, String tagName) {
        EscapeRoomSession escapeRoomSession = null;
        EscapeRoomSessionResponse response = null;
        String userName = KeycloakUserUtil.getCurrentUsername();

        if(userName != null && !userName.isEmpty()) {
            escapeRoomSession = sessionService.addTagToSession(UUID.fromString(escapeRoomSessionId), tagName);
        }
        if(escapeRoomSession != null) {
            response = EscapeRoomSessionMapperUtil.map(escapeRoomSession);
        }

        return new ResponseEntity<>(response, escapeRoomSession != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<EscapeRoomSessionResponse> deleteERTag(String escapeRoomSessionId, String tagName) {
        EscapeRoomSession escapeRoomSession = null;
        EscapeRoomSessionResponse response = null;
        String userName = KeycloakUserUtil.getCurrentUsername();

        if(userName != null && !userName.isEmpty()) {
            escapeRoomSession = sessionService.removeTagFromSession(UUID.fromString(escapeRoomSessionId), tagName);
        }
        if(escapeRoomSession != null) {
            response = EscapeRoomSessionMapperUtil.map(escapeRoomSession);
        }

        return new ResponseEntity<>(response, escapeRoomSession != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
