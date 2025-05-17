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
    public ResponseEntity<List<SessionResponse>> getERSessionByTagOrPin(String tag, Integer pin) {
        UUID userId = KeycloakUserUtil.getCurrentUserUUID()
                .orElseThrow(() -> new NoSuchElementException("No userUUID found"));

        try {
            List<EscapeRoomSession> sessions;

            if (tag != null && pin != null) {
                return ResponseEntity.badRequest().build();
            } else if (tag != null) {
                sessions = sessionService.getSessionsByTags(userId, List.of(tag));
            } else if (pin != null) {
                EscapeRoomSession session = sessionService.getSessionByRoomPin(pin.longValue());
                sessions = session != null ? List.of(session) : List.of();
            } else {
                return ResponseEntity.badRequest().build();
            }

            List<SessionResponse> response = sessions.stream().map(EscapeRoomSessionMapperUtil::map).toList();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching sessions by tag or pin: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<SessionResponse> getERSessionByPin(Integer pin) {
        try {
            EscapeRoomSession session = sessionService.getSessionByRoomPin(pin.longValue());
            return new ResponseEntity<>(EscapeRoomSessionMapperUtil.map(session), HttpStatus.OK);
        } catch (Exception e) {
            log.debug("Room pin {} not found: {}", pin, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
