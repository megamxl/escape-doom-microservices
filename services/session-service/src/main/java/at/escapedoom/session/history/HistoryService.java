package at.escapedoom.session.history;

import at.escapedoom.session.data.entity.EscapeRoomSession;
import at.escapedoom.session.rest.api.HistoryApiDelegate;
import at.escapedoom.session.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.session.service.EscapeRoomSessionService;
import at.escapedoom.session.util.KeycloakUserUtil;
import at.escapedoom.session.util.EscapeRoomSessionMapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.*;

@Component
public class HistoryService implements HistoryApiDelegate {

    private final EscapeRoomSessionService sessionService;

    public HistoryService(EscapeRoomSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return HistoryApiDelegate.super.getRequest();
    }

    @PreAuthorize("hasRole('LECTOR')")
    @Override
    public ResponseEntity<List<EscapeRoomSessionResponse>> getERHistory() {
         String userName = KeycloakUserUtil.getCurrentUsername();
         List<EscapeRoomSession> sessions = sessionService.getSessionsByUserName(userName);
         List<EscapeRoomSessionResponse> response = new ArrayList<>();

         for(EscapeRoomSession session : sessions){
             response.add(EscapeRoomSessionMapperUtil.map(session));
         }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
