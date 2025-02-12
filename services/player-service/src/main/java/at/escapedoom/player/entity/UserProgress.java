package at.escapedoom.player.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static at.escapedoom.player.utils.EntityConstants.RESULT_JOIN_COLUMN;

@Entity
@Table(name = "user_progress")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false, name = RESULT_JOIN_COLUMN )
    private UUID userIdentifier;

    private String userName;

    private Long currentEscapeRoomLevel;

    private LocalDateTime lastRiddleSolvedAt;

    private Long currentPoints;

}
