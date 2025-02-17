package at.escapedoom.session.service;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.data.repository.EscapeRoomSessionService;
import at.escapedoom.session.rest.api.ManagementApiDelegate;
import at.escapedoom.session.rest.model.EscapeRoomCreation;
import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.session.rest.model.EscapeRoomState;
import at.escapedoom.session.util.KeycloakUserUtil;
import at.escapedoom.session.util.EscapeRoomSessionMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.*;

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
    public ResponseEntity<EscapeRoomSessionResponse> createERInstance(EscapeRoomCreation escapeRoomCreation) {
        EscapeRoomSession escapeRoomSession = null;
        EscapeRoomSessionResponse response = null;
        String userName = KeycloakUserUtil.getCurrentUsername();

        Random random = new Random();
        Integer roomPin = 100000 + random.nextInt(900000);

        if (userName != null && !userName.isEmpty()) {
            try {
                escapeRoomSession = sessionService.createSession(escapeRoomCreation.getEscapeRoomTemplateId(),
                        escapeRoomCreation.getPlayTime().longValue(), roomPin.longValue(), userName);
            } catch (Exception e) {
                if (e instanceof DataIntegrityViolationException) {
                    roomPin = 100000 + random.nextInt(900000);
                    escapeRoomSession = sessionService.createSession(escapeRoomCreation.getEscapeRoomTemplateId(),
                            escapeRoomCreation.getPlayTime().longValue(), roomPin.longValue(), userName);
                }
            }
        }

        if (escapeRoomSession != null) {
            response = EscapeRoomSessionMapperUtil.map(escapeRoomSession);
        }

        return new ResponseEntity<>(response, escapeRoomSession != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<EscapeRoomSessionResponse> toggleERInstanceState(UUID escapeRoomSessionId,
            EscapeRoomState state) {

        EscapeRoomSession escapeRoomSession = sessionService.getSessionById(escapeRoomSessionId);

        if (escapeRoomSession == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!escapeRoomSession.getUserId().equals(KeycloakUserUtil.getCurrentUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        escapeRoomSession = sessionService.changeSessionStatus(escapeRoomSessionId, state);
        return new ResponseEntity<>(EscapeRoomSessionMapperUtil.map(escapeRoomSession), HttpStatus.OK);
    }

}
