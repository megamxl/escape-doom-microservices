package at.escapedoom.session.service.delegate;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.api.HistoryApiDelegate;
import at.escapedoom.session.rest.model.SessionResponse;
import at.escapedoom.session.service.EscapeRoomSessionService;
import at.escapedoom.session.util.EscapeRoomSessionMapperUtil;
import at.escapedoom.spring.security.KeycloakUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.*;

@RequiredArgsConstructor
@Component
public class HistoryApiDelegateImpl implements HistoryApiDelegate {

    private final EscapeRoomSessionService sessionService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return HistoryApiDelegate.super.getRequest();
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<List<SessionResponse>> getERHistory() {
        UUID userId = KeycloakUserUtil.getCurrentUserUUID()
                .orElseThrow(() -> new NoSuchElementException("No userUUID found"));
        List<EscapeRoomSession> sessions = sessionService.getSessionsByUserUUID(userId);
        List<SessionResponse> response = new ArrayList<>();

        for (EscapeRoomSession session : sessions) {
            response.add(EscapeRoomSessionMapperUtil.map(session));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
