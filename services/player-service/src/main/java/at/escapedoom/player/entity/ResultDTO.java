package at.escapedoom.player.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ResultDTO {

    private Long escapeRoomLevel;

    private String input;

    private LocalDateTime solvedLevelAt;

    private Double awardedPoints;
}
