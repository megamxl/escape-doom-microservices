package at.escapedoom.leaderboard.rest.api;

import at.escapedoom.leaderboard.rest.model.FullExportRoomPinGet200ResponseInner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link AllLeaderboardsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public interface AllLeaderboardsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /escape-room-sessions : Get all session leaderboards
     * Retrieve leaderboards for all sessions, optionally filtered by session tags.
     *
     * @param tags A comma-separated list of tags to filter sessions. (optional)
     * @return All session leaderboards (status code 200)
     *         or No sessions found (status code 404)
     * @see AllLeaderboardsApi#escapeRoomSessionsGet
     */
    default ResponseEntity<List<FullExportRoomPinGet200ResponseInner>> escapeRoomSessionsGet(List<String> tags) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"session\" : { \"room_pin\" : 420666, \"escape_room_template_id\" : \"ff95c83b-c8da-4180-8a97-09dc91892a01\", \"state\" : \"FINISHED\", \"play_time\" : 60, \"escape_room_session_id\" : \"f1d948b9-2928-42f5-a5c8-0af5711514dc\", \"tags\" : [ \"SDE26\", \"CSDC\" ] }, \"user_progress\" : [ { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ], \"escape_room_session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\" }, { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ], \"escape_room_session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\" } ] }, { \"session\" : { \"room_pin\" : 420666, \"escape_room_template_id\" : \"ff95c83b-c8da-4180-8a97-09dc91892a01\", \"state\" : \"FINISHED\", \"play_time\" : 60, \"escape_room_session_id\" : \"f1d948b9-2928-42f5-a5c8-0af5711514dc\", \"tags\" : [ \"SDE26\", \"CSDC\" ] }, \"user_progress\" : [ { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ], \"escape_room_session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\" }, { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ], \"escape_room_session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\" } ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
