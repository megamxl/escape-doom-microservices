package at.escapedoom.player.service.delegates;

import at.escapedoom.player.rest.api.LevelApiDelegate;
import at.escapedoom.player.rest.model.EscapeRoomLevel;
import at.escapedoom.player.rest.model.EscapeRoomResult;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import at.escapedoom.player.service.GamePlayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GamePlayDelegateService implements LevelApiDelegate {

    private final GamePlayService gamePlayService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return LevelApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<EscapeRoomLevel> getLevelOfSessionByPlayerSessionID(UUID playerSessionId) {

        gamePlayService.getCurrentLevelByUserIdentifier(playerSessionId);

        return new ResponseEntity<>(EscapeRoomLevel.builder().build(), HttpStatus.OK);
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
