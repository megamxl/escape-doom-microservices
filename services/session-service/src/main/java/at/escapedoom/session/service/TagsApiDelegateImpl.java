package at.escapedoom.session.service;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.data.repository.EscapeRoomSessionService;
import at.escapedoom.session.rest.api.TagsApiDelegate;
import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.spring.security.KeycloakUserUtil;
import at.escapedoom.session.util.EscapeRoomSessionMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TagsApiDelegateImpl implements TagsApiDelegate {

    private final EscapeRoomSessionService sessionService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return TagsApiDelegate.super.getRequest();
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<EscapeRoomSessionResponse> addERTag(String escapeRoomSessionId, String tagName) {
        EscapeRoomSession escapeRoomSession = null;
        EscapeRoomSessionResponse response = null;
        UUID userId = KeycloakUserUtil.getCurrentUserUUID()
                .orElseThrow(() -> new NoSuchElementException("No userUUID found"));

        System.out.println(userId.toString());
        escapeRoomSession = sessionService.addTagToSession(userId, UUID.fromString(escapeRoomSessionId), tagName);

        if (escapeRoomSession != null) {
            response = EscapeRoomSessionMapperUtil.map(escapeRoomSession);
        }

        return new ResponseEntity<>(response, escapeRoomSession != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<EscapeRoomSessionResponse> deleteERTag(String escapeRoomSessionId, String tagName) {
        EscapeRoomSession escapeRoomSession = null;
        EscapeRoomSessionResponse response = null;
        UUID userId = KeycloakUserUtil.getCurrentUserUUID()
                .orElseThrow(() -> new NoSuchElementException("No userUUID found"));

        escapeRoomSession = sessionService.removeTagFromSession(userId, UUID.fromString(escapeRoomSessionId), tagName);

        if (escapeRoomSession != null) {
            response = EscapeRoomSessionMapperUtil.map(escapeRoomSession);
        }

        return new ResponseEntity<>(response, escapeRoomSession != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
