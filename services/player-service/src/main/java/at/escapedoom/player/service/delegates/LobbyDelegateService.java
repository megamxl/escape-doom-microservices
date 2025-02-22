package at.escapedoom.player.service.delegates;

import at.escapedoom.player.rest.api.LobbyApiDelegate;
import at.escapedoom.player.rest.model.*;
import at.escapedoom.player.service.LobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LobbyDelegateService implements LobbyApiDelegate {

    private final LobbyService lobbyService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return LobbyApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<EscapeRoomJoinResponse> handlePlayerJoin(EscapeRoomJoin escapeRoomJoin) {

        EscapeRoomJoinResponse escapeRoomJoinResponse = lobbyService
                .joinSessionByRoomPin(escapeRoomJoin.getRoomPin().longValue(), escapeRoomJoin.getPlayerName());

        return ResponseEntity.ok(escapeRoomJoinResponse);
    }
}
