package at.escapedoom.session.service.delegate;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.api.ManagementApiDelegate;
import at.escapedoom.session.rest.model.EscapeRoomCreation;
import at.escapedoom.session.rest.model.EscapeRoomState;
import at.escapedoom.session.rest.model.SessionResponse;
import at.escapedoom.session.service.EscapeRoomSessionService;
import at.escapedoom.session.util.EscapeRoomSessionMapperUtil;
import at.escapedoom.spring.security.KeycloakUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ManagementApiDelegateImpl implements ManagementApiDelegate {

    private final EscapeRoomSessionService sessionService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ManagementApiDelegate.super.getRequest();
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<SessionResponse> createERInstance(EscapeRoomCreation escapeRoomCreation) {
        EscapeRoomSession escapeRoomSession = null;
        SessionResponse response = null;
        UUID userId = KeycloakUserUtil.getCurrentUserUUID()
                .orElseThrow(() -> new NoSuchElementException("No userUUID found"));

        Random random = new Random();
        Integer roomPin = 100000 + random.nextInt(900000);

        try {
            escapeRoomSession = sessionService.createSession(escapeRoomCreation.getTemplateId(),
                    escapeRoomCreation.getPlayTime().longValue(), roomPin.longValue(), userId);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                roomPin = 100000 + random.nextInt(900000);
                escapeRoomSession = sessionService.createSession(escapeRoomCreation.getTemplateId(),
                        escapeRoomCreation.getPlayTime().longValue(), roomPin.longValue(), userId);
            }
        }

        if (escapeRoomSession != null) {
            response = EscapeRoomSessionMapperUtil.map(escapeRoomSession);
        }

        return new ResponseEntity<>(response, escapeRoomSession != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<SessionResponse> toggleERInstanceState(UUID escapeRoomSessionId, EscapeRoomState state) {

        EscapeRoomSession escapeRoomSession = sessionService.getSessionById(escapeRoomSessionId);

        UUID userId = KeycloakUserUtil.getCurrentUserUUID()
                .orElseThrow(() -> new NoSuchElementException("No userUUID found"));

        if (escapeRoomSession == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!escapeRoomSession.getUserId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        escapeRoomSession = sessionService.changeSessionStatus(escapeRoomSessionId, state);
        return new ResponseEntity<>(EscapeRoomSessionMapperUtil.map(escapeRoomSession), HttpStatus.OK);
    }

}
