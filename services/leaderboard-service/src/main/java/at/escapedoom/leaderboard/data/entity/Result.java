package at.escapedoom.leaderboard.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "result")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long escapeRoomLevel;

    @Column(length = 10000)
    private String input;

    private LocalDateTime solvedLevelAt;

    private Double awardedPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_identifier")
    private UserProgress userProgress;

}
