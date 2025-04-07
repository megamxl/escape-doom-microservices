package at.escapedoom.session.service.delegate;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.api.SessionApiDelegate;
import at.escapedoom.session.rest.model.SessionResponse;
import at.escapedoom.session.service.EscapeRoomSessionService;
import at.escapedoom.session.util.EscapeRoomSessionMapperUtil;
import at.escapedoom.spring.security.KeycloakUserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.*;

@RequiredArgsConstructor
@Component
@Slf4j
public class SessionApiDelegateImpl implements SessionApiDelegate {

    private final EscapeRoomSessionService sessionService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return SessionApiDelegate.super.getRequest();
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<List<SessionResponse>> getERByTags(List<String> tags) {
        UUID userId = KeycloakUserUtil.getCurrentUserUUID()
                .orElseThrow(() -> new NoSuchElementException("No userUUID found"));

        List<SessionResponse> response = new ArrayList<>();

        try {
            List<EscapeRoomSession> escapeRoomSessions = sessionService.getSessionsByTags(userId, tags);
            for (EscapeRoomSession session : escapeRoomSessions) {
                response.add(EscapeRoomSessionMapperUtil.map(session));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<SessionResponse> getERSessionByPin(Integer roomPin) {
        try {
            EscapeRoomSession escapeRoomSession = sessionService.getSessionByRoomPin(roomPin.longValue());
            return new ResponseEntity<>(EscapeRoomSessionMapperUtil.map(escapeRoomSession), HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
