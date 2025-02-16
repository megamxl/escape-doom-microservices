package at.escapedoom.session.data.entity;

import at.escapedoom.session.rest.model.EscapeRoomState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "escape_room_session")
public class EscapeRoomSession {
    @Id
    private UUID escapeRoomSessionId;
    private UUID escapeRoomTemplateId;

    @Column(unique = true, nullable = false)
    private Long roomPin;

    private String userId;
    private Long playTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private EscapeRoomState state;

    @ElementCollection
    @CollectionTable(name = "escape_room_tags", joinColumns = @JoinColumn(name = "escape_room_session_id"))
    @Column(name = "tag")
    private List<String> tags;

}
