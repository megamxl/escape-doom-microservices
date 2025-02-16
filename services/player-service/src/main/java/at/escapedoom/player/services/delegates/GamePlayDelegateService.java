package at.escapedoom.player.services.delegates;

import at.escapedoom.player.rest.api.LevelApiDelegate;
import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.rest.model.EscapeRoomResult;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;

@Component
public class GamePlayDelegateService implements LevelApiDelegate {
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return LevelApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<EscapeRoomLevel> getLevelOfSessionByPlayerSessionID(UUID playerSessionId) {
        return LevelApiDelegate.super.getLevelOfSessionByPlayerSessionID(playerSessionId);
    }

    @Override
    public ResponseEntity<EscapeRoomResult> getLevelResult(UUID playerSessionId) {
        return LevelApiDelegate.super.getLevelResult(playerSessionId);
    }

    @Override
    public ResponseEntity<Void> submitSolutionAttemptForCurrentLevel(UUID playerSessionId,
            EscapeRoomSolutionSubmition escapeRoomSolutionSubmition) {
        return LevelApiDelegate.super.submitSolutionAttemptForCurrentLevel(playerSessionId,
                escapeRoomSolutionSubmition);
    }
}
