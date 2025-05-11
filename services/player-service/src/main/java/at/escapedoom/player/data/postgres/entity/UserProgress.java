package at.escapedoom.player.data.postgres.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static at.escapedoom.player.utils.EntityConstants.RESULT_JOIN_COLUMN;

@Entity
@Table(
        name = "user_progress",
        uniqueConstraints =
            @UniqueConstraint(columnNames = {"roomPin", "userName"})
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false, name = RESULT_JOIN_COLUMN)
    private UUID userIdentifier;

    private String userName;

    private Long roomPin;

    private Long currentEscapeRoomLevel;

    private LocalDateTime lastRiddleSolvedAt;

    private Long currentPoints;

    private UUID templateID;

}
