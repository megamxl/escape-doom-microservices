package at.escapedoom.session.service;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.data.repository.EscapeRoomSessionService;
import at.escapedoom.session.rest.api.SessionApiDelegate;
import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.session.util.EscapeRoomSessionMapperUtil;
import at.escapedoom.session.util.KeycloakUserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<EscapeRoomSessionResponse>> getERByTags(List<String> tags) {
        String userName = KeycloakUserUtil.getCurrentUsername();
        List<EscapeRoomSessionResponse> response = new ArrayList<>();
        if (userName != null && !userName.isEmpty()) {
            try {
                List<EscapeRoomSession> escapeRoomSessions = sessionService.getSessionsByTags(userName, tags);
                for (EscapeRoomSession session : escapeRoomSessions) {
                    response.add(EscapeRoomSessionMapperUtil.map(session));
                }
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.debug(e.getMessage());
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<EscapeRoomSessionResponse> getERSessionByPin(Integer roomPin) {
        try {
            EscapeRoomSession escapeRoomSession = sessionService.getSessionByRoomPin(roomPin.longValue());
            return new ResponseEntity<>(EscapeRoomSessionMapperUtil.map(escapeRoomSession), HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
