package at.escapedoom.leaderboard.service.delegate;

import at.escapedoom.leaderboard.rest.api.AllLeaderboardsApiDelegate;
import at.escapedoom.leaderboard.rest.model.FullExportResponse;
import at.escapedoom.leaderboard.service.EscapeRoomLeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AllLeaderboardsApiDelegateImpl implements AllLeaderboardsApiDelegate {

    private final EscapeRoomLeaderBoardService escapeRoomLeaderBoardService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return AllLeaderboardsApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<List<FullExportResponse>> escapeRoomSessionsGet(String tag) {
        List<FullExportResponse> resp = escapeRoomLeaderBoardService.getLeaderBoardsOfSessionsWithTag(tag);
        return ResponseEntity.of(Optional.ofNullable(resp));
    }
}
