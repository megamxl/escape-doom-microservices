package at.escapedoom.leaderboard.service.delegate;

import at.escapedoom.leaderboard.rest.api.SessionLeaderboardApiDelegate;
import at.escapedoom.leaderboard.rest.model.FullExportResponse;
import at.escapedoom.leaderboard.rest.model.UserProgress;
import at.escapedoom.leaderboard.service.EscapeRoomLeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SessionLeaderboardApiDelegateImpl implements SessionLeaderboardApiDelegate {

    private final EscapeRoomLeaderBoardService escapeRoomLeaderBoardService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return SessionLeaderboardApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<List<FullExportResponse>> fullExportRoomPinGet(Integer roomPin) {
        List<FullExportResponse> userProgresses = escapeRoomLeaderBoardService.getFullLeaderBoardByRoomPin(roomPin);
        return ResponseEntity.of(Optional.ofNullable(userProgresses));
    }

    @Override
    public ResponseEntity<List<UserProgress>> roomPinGet(Integer roomPin) {
        List<UserProgress> userProgresses = escapeRoomLeaderBoardService.getLeaderBoardByRoomPin(roomPin);
        return ResponseEntity.of(Optional.ofNullable(userProgresses));
    }

}
