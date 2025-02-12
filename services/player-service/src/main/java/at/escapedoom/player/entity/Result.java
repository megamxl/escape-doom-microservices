package at.escapedoom.player.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static at.escapedoom.player.utils.EntityConstants.RESULT_JOIN_COLUMN;

@Entity
@Table(name = "result")
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
    @JoinColumn(name = RESULT_JOIN_COLUMN)
    private UserProgress userProgress;

}
