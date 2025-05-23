package at.escapedoom.leaderboard.rest.api;

import at.escapedoom.leaderboard.rest.model.FullExportResponse;
import at.escapedoom.leaderboard.rest.model.UserProgress;
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
 * A delegate to be called by the {@link SessionLeaderboardApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
public interface SessionLeaderboardApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /full-export/{room_pin} : Retrieve full leaderboard Retrieve full leaderboard for a specific escape room
     * session, showing progress for all users.
     *
     * @param roomPin
     *            The unique ID of the escape room session. (required)
     *
     * @return Full leaderboard (status code 200)
     *
     * @see SessionLeaderboardApi#fullExportRoomPinGet
     */
    default ResponseEntity<List<FullExportResponse>> fullExportRoomPinGet(Integer roomPin) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"session\" : { \"room_pin\" : 420666, \"session_id\" : \"f1d948b9-2928-42f5-a5c8-0af5711514dc\", \"template_id\" : \"ff95c83b-c8da-4180-8a97-09dc91892a01\", \"state\" : \"FINISHED\", \"play_time\" : 60, \"tags\" : [ \"SDE26\", \"CSDC\" ] }, \"user_progress\" : [ { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ] }, { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ] } ] }, { \"session\" : { \"room_pin\" : 420666, \"session_id\" : \"f1d948b9-2928-42f5-a5c8-0af5711514dc\", \"template_id\" : \"ff95c83b-c8da-4180-8a97-09dc91892a01\", \"state\" : \"FINISHED\", \"play_time\" : 60, \"tags\" : [ \"SDE26\", \"CSDC\" ] }, \"user_progress\" : [ { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ] }, { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ] } ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /{room_pin} : Get current session leaderboard Retrieve the leaderboard for a specific escape room session,
     * showing progress for all users.
     *
     * @param roomPin
     *            The unique ID of the escape room session. (required)
     *
     * @return Current session leaderboard (status code 200) or Session not found (status code 404)
     *
     * @see SessionLeaderboardApi#roomPinGet
     */
    default ResponseEntity<List<UserProgress>> roomPinGet(Integer roomPin) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ] }, { \"score\" : 30, \"last_riddle_solved_at\" : \"2025-01-02T15:45:00Z\", \"current_escape_room_level\" : 5, \"session_id\" : \"a32d8f8c-f2f4-4c4d-b9c3-e5a7d7f6e8f0\", \"player_name\" : \"Waschbär\", \"results\" : [ { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" }, { \"input\" : \"Solution Attempt\", \"added_points\" : 100, \"current_escape_room_level\" : 3, \"solved_date\" : \"2025-01-02T15:45:00Z\" } ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
