package at.escapedoom.leaderboard.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_progress", uniqueConstraints = @UniqueConstraint(columnNames = { "roomPin", "userName" }))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false, name = "user_identifier")
    private UUID userIdentifier;

    private String userName;

    private Long roomPin;

    private Long currentEscapeRoomLevel;

    private LocalDateTime lastRiddleSolvedAt;

    private Long currentPoints;

    @Column(name = "template_id")
    private UUID templateID;

}
