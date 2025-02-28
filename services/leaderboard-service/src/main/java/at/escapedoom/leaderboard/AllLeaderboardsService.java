package at.escapedoom.leaderboard;

import at.escapedoom.leaderboard.rest.api.AllLeaderboardsApiDelegate;
import at.escapedoom.leaderboard.rest.model.EscapeRoomSessionResponse;
import at.escapedoom.leaderboard.rest.model.FullExportRoomPinGet200ResponseInner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AllLeaderboardsService implements AllLeaderboardsApiDelegate {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return AllLeaderboardsApiDelegate.super.getRequest();
    }

    // @Override
    // public ResponseEntity<List<FullExportRoomPinGet200ResponseInner>> escapeRoomSessionsGet(List<String> tags) {
    // FullExportRoomPinGet200ResponseInner fullExportRoomPinGet200ResponseInner = new
    // FullExportRoomPinGet200ResponseInner();
    // fullExportRoomPinGet200ResponseInner
    // .setSession(new EscapeRoomSessionResponse().escapeRoomSessionId(UUID.randomUUID()));
    // return ResponseEntity.of(Optional.of(List.of(fullExportRoomPinGet200ResponseInner)));
    // }
}
