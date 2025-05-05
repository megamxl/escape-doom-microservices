package at.escapedoom.player.data.postgres.entity;

import at.escapedoom.player.rest.model.EscapeRoomResult;
import at.escapedoom.player.rest.model.EscapeRoomSolutionSubmition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "solution_attempt")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolutionAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solutionAttemptId;

    // TODO evaluate if more entries are allowed
    @Column(unique = true)
    private UUID playerUUID;

    @Enumerated(EnumType.STRING)
    private EscapeRoomSolutionSubmition.LanguageEnum language;

    private Long currentEscapeRoomLevel;

    @Column(length = 1024)
    private String codeSubmition;

    @Column(length = 1024)
    private String output;

    @Enumerated(EnumType.STRING)
    private EscapeRoomResult.StatusEnum status;
}
